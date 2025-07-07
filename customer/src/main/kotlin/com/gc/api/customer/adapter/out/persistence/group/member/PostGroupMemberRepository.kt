package com.gc.api.customer.adapter.out.persistence.group.member

import com.gc.api.customer.application.port.out.persistence.group.member.CreateGroupMemberPort
import com.gc.api.customer.application.service.dto.group.CreateGroupDto
import com.gc.api.customer.application.service.dto.group.member.JoinGroupMemberDto
import com.gc.api.customer.domain.model.group.Group
import com.gc.storage.document.group.GroupMemberDocument
import com.gc.storage.document.group.GroupMemberMongoRepository
import org.springframework.stereotype.Repository

@Repository
class PostGroupMemberRepository(
    private val groupMemberRepository: GroupMemberMongoRepository,
): CreateGroupMemberPort {

    // TODO: 추후 삭제
    override fun registerGroupMember(request: CreateGroupDto, group: Group) {
        groupMemberRepository.save(
            createGroupMemberDocument(request, group)
        )
    }

    override fun joinGroup(request: JoinGroupMemberDto) {
        groupMemberRepository.save(
            createGroupMemberDocument(request)
        )
    }

    private fun createGroupMemberDocument(request: JoinGroupMemberDto): GroupMemberDocument {
        return GroupMemberDocument(
            groupId = request.groupId,
            memberId = request.memberId,
            nickname = request.nickname,
            profile = request.profile,
        )
    }

    // TODO: 추후 삭제
    private fun createGroupMemberDocument(request: CreateGroupDto, group: Group): GroupMemberDocument {
        return GroupMemberDocument(
            memberId = request.memberId,
            nickname = request.memberNickname,
            profile = request.memberProfile,
            groupId = group.id,
        )
    }
}