package com.gc.api.customer.domain.service.group.member

import com.gc.api.customer.application.port.out.persistence.group.member.GetGroupMemberPort
import com.gc.api.customer.domain.model.group.GroupMember
import org.springframework.stereotype.Service

@Service
class GroupMemberQueryService(
    private val getGroupMemberPort: GetGroupMemberPort,
) {

    fun findGroupMembers(groupId: String): List<GroupMember> {
        return getGroupMemberPort.getGroupMembers(groupId)
    }

    suspend fun findAllGroupMembers(groupIds: List<String>): Map<String, List<GroupMember>> {
        val allGroupMembers: List<GroupMember> = getGroupMemberPort.getAllGroupMembers(groupIds)
        return allGroupMembers.groupBy { it.groupId }
    }
}