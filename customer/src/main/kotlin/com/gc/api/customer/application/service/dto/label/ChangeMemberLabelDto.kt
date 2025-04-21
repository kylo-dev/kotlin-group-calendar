package com.gc.api.customer.application.service.dto.label

data class ChangeMemberLabelDto(
    val memberId: String,
    val labelId: String,
    val labelName: String?,
    val labelColor: String?,
)