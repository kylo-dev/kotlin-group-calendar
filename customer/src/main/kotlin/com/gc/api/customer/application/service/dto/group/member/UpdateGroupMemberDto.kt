package com.gc.api.customer.application.service.dto.group.member

data class UpdateGroupMemberDto(
    val groupId: String,
    val memberId: String,
    val memberNickname: String?,
    val memberProfile: String?,
)
