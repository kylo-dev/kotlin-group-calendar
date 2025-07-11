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
import com.gc.common.logging.logger
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
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
            logger.info { "[${Thread.currentThread().name}] getTop3EventsByGroup 실행" }
            groupEventQueryService.getTop3EventsByGroup(groupIds)
        }

        // 3. 그룹 내 속한 멤버 조회
        // query: n번 (groups 크기만큼) -> query 1번 (groups)
        val groupMemberMapDeferred = async {
            logger.info { "[${Thread.currentThread().name}] findAllGroupMembers 실행" }
            groupMemberQueryService.findAllGroupMembers(groupIds)
        }

        // 결과 await
        val top3EventsByGroupMap = top3GroupEventsMapDeferred.await()
        val groupMemberMap = groupMemberMapDeferred.await()

        logger.info { "[${Thread.currentThread().name}] 코루틴 작업 완료" }

        groups.map { group ->
            val groupId = group.id
            val groupMembers = groupMemberMap[groupId] ?: emptyList()
            val groupEvents = top3EventsByGroupMap[groupId] ?: emptyList()
            GroupOverview.of(group, groupMembers, groupEvents)
        }
    }
}