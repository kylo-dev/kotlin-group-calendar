package com.gc.api.customer.adapter.`in`.dto.request.member

import com.gc.api.customer.application.service.dto.member.ChangeMemberDto

data class ChangeMemberRequest(
    val nickname: String?,
    val profile: String?,
) {
    fun toServiceRequest(memberId: String): ChangeMemberDto {
        return ChangeMemberDto(
            nickname = this.nickname,
            profile = this.profile,
            memberId = memberId,
        )
    }
}