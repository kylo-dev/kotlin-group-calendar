package com.gc.api.customer.adapter.`in`.dto.request.label

import com.gc.api.customer.application.service.dto.label.ChangeMemberLabelDto
import com.gc.api.customer.domain.model.member.Member

data class ChangeLabelRequest(
    val labelName: String?,
    val labelColor: String?,
) {
    fun toServiceRequest(request: ChangeLabelRequest, labelId: String, member: Member): ChangeMemberLabelDto {
        return ChangeMemberLabelDto(
            member.id,
            labelId,
            request.labelName,
            request.labelColor)
    }
}
