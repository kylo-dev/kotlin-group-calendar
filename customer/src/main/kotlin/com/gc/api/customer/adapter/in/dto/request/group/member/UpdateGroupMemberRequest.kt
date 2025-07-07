package com.gc.api.customer.adapter.`in`.dto.request.group.member

import com.gc.api.customer.application.service.dto.group.member.UpdateGroupMemberDto
import com.gc.api.customer.domain.model.member.Member

data class UpdateGroupMemberRequest(
    val memberNickname: String?,
    val memberProfile: String?,
) {
    fun toServiceRequest(groupId: String, member: Member): UpdateGroupMemberDto {
        return UpdateGroupMemberDto(
            groupId,
            member.id,
            memberNickname,
            memberProfile,
        )
    }
}
