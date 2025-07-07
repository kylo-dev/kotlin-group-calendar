package com.gc.api.customer.application.port.out.persistence.group.member

import com.gc.api.customer.application.service.dto.group.member.UpdateGroupMemberDto

interface UpdateGroupMemberPort {
    fun updateGroupMember(request: UpdateGroupMemberDto)
}