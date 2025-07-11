package com.gc.api.customer.application.service.facade.group

import com.gc.api.customer.application.service.dto.group.CreateGroupDto
import com.gc.api.customer.domain.model.group.Group
import com.gc.api.customer.domain.model.group.GroupOverview
import com.gc.api.customer.domain.model.member.Member
import com.gc.api.customer.domain.service.group.GroupCommandService
import com.gc.api.customer.domain.service.group.GroupQueryService
import com.gc.api.customer.domain.service.group.member.GroupMemberCommandService
import com.gc.api.customer.domain.service.group.member.GroupMemberQueryService
import com.gc.api.customer.domain.service.group_event.GroupEventQueryService
import com.gc.api.customer.framework.exception.toCoroutineException
import com.gc.common.logging.logger
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import org.springframework.stereotype.Service

@Service
class GroupFacade(
//    private val inviteTokenProvider: InviteTokenProvider,
    private val groupCommandService: GroupCommandService,
    private val groupQueryService: GroupQueryService,
    private val groupMemberCommandService: GroupMemberCommandService,
    private val groupMemberQueryService: GroupMemberQueryService,
    private val groupEventQueryService: GroupEventQueryService,
) {

    fun createGroup(createGroupDto: CreateGroupDto): Group {
        // 1. 그룹 생성
        val newGroup = groupCommandService.createGroup(createGroupDto)
        // 2. 그룹 참가
        groupMemberCommandService.joinGroup(createGroupDto.toJoinGroupDto(newGroup))
        return newGroup
    }

    suspend fun findAllGroupByMember(member: Member): List<GroupOverview> = coroutineScope {

        // 1. 사용자가 속한 모든 그룹 조회
        // query: 2번 (GroupMemberDoc -> GroupDoc)
        val groups: List<Group> = groupQueryService.findGroupsByMemberId(member.id)
        val groupIds = groups.map { it.id }

        // 2. 그룹 내 확정된 약속 조회 (최대 3개씩)
        val top3GroupEventsMapDeferred = async {
            withRetry {
                groupEventQueryService.getTop3EventsByGroup(groupIds)
            }
        }

        // 3. 그룹 내 속한 멤버 조회
        // query: n번 (groups 크기만큼) -> query 1번 (groups)
        val groupMemberMapDeferred = async {
            withRetry {
                groupMemberQueryService.findAllGroupMembers(groupIds)
            }
        }

        // 결과 await
        val top3EventsByGroupMap = runCatching {
            top3GroupEventsMapDeferred.await()
        }.onFailure {logger.error { "그룹 내 Top3 약속 조회 실패: $it" }
        }.getOrElse { throw it }

        val groupMemberMap = runCatching {
            groupMemberMapDeferred.await()
        }.onFailure {logger.error { "그룹원 약속 조회 실패: $it" }
        }.getOrElse { throw it }

        groups.map { group ->
            val groupId = group.id
            val groupMembers = groupMemberMap[groupId] ?: emptyList()
            val groupEvents = top3EventsByGroupMap[groupId] ?: emptyList()
            GroupOverview.of(group, groupMembers, groupEvents)
        }
    }

    private suspend fun <T> withRetry(
        maxAttempt: Int = 3,
        delayMillis: Long = 300,
        block: suspend () -> T,
    ): T {
        var lastException: Throwable? = null
        repeat(maxAttempt) { attempt ->
            try {
                return block()
            } catch (e: Exception) {
                logger.warn { "[Retry attempt ${attempt + 1}] failed, $e" }
                lastException = e
                if (attempt < maxAttempt - 1) delay(delayMillis)
            }
        }
        throw toCoroutineException(lastException!!)
    }
}