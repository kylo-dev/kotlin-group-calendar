package com.gc.api.customer.application.service.dto.member

data class ChangeMemberDto(
    val memberId: String,
    val nickname: String?,
    val profile: String?,
)
