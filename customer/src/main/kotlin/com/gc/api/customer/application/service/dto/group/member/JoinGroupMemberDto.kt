package com.gc.api.customer.application.service.dto.group.member

data class JoinGroupMemberDto(
    val groupId: String,
    val memberId: String,
    val nickname: String,
    val profile: String?,
)
