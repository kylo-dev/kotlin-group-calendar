package com.gc.api.customer.application.port.out.persistence.group.member

import com.gc.api.customer.domain.model.group.GroupMember

interface GetGroupMemberPort {
    fun getGroupMembers(groupId: String): List<GroupMember>
}