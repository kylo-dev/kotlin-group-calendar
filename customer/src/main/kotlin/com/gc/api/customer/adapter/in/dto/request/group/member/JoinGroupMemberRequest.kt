package com.gc.api.customer.adapter.`in`.dto.request.group.member

import com.gc.api.customer.application.service.dto.group.member.JoinGroupMemberDto
import com.gc.api.customer.domain.model.member.Member

data class JoinGroupMemberRequest(
    val nickname: String,
    val profile: String?
) {
    fun toServiceRequest(groupId: String, member: Member): JoinGroupMemberDto {
        return JoinGroupMemberDto(
            groupId = groupId,
            memberId = member.id,
            nickname = nickname,
            profile = profile,
        )
    }
}