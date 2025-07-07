package com.gc.api.customer.application.service.dto.group

data class UpdateGroupDto(
    val groupId: String,
    val groupName: String?,
    val groupDescription: String?,
)
