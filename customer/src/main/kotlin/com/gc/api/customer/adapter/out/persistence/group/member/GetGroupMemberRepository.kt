package com.gc.api.customer.adapter.out.persistence.group.member

import com.gc.api.customer.application.port.out.persistence.group.member.GetGroupMemberPort
import com.gc.api.customer.domain.model.group.GroupMember
import com.gc.storage.document.group.GroupMemberDocument
import com.gc.storage.document.group.GroupMemberMongoRepository
import org.springframework.stereotype.Repository

@Repository
class GetGroupMemberRepository(
    private val groupMemberMongoRepository: GroupMemberMongoRepository,
): GetGroupMemberPort {

    override fun getGroupMembers(groupId: String): List<GroupMember> {
        val results = groupMemberMongoRepository.findAllByGroupId(groupId)
        return results.map { documentToModel(it) }
    }

    private fun documentToModel(document: GroupMemberDocument): GroupMember {
        return GroupMember(
            groupId = document.groupId,
            memberId = document.memberId,
            memberNickname = document.nickname,
            memberProfile = document.profile,
        )
    }
}