package com.gc.api.customer.application.service.dto.group

data class CreateGroupDto(
    val groupName: String,
    val groupProfile: String,
    val groupDescription: String,
    val memberId: String,
    val memberNickname: String,
    val memberProfile: String,
)