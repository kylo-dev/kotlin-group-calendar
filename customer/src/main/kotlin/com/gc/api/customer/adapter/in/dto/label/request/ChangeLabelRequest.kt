package com.gc.api.customer.adapter.`in`.dto.label.request

import com.gc.api.customer.application.service.dto.label.ChangeMemberLabelDto

data class ChangeLabelRequest(
    val labelId: String,
    val labelName: String?,
    val labelColor: String?,
) {
    fun toServiceRequest(request: ChangeLabelRequest, memberId: String): ChangeMemberLabelDto {
        return ChangeMemberLabelDto(
            memberId,
            request.labelId,
            request.labelName,
            request.labelColor)
    }
}
