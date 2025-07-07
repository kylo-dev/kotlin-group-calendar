package com.gc.api.customer.adapter.`in`.dto.request.group

import com.gc.api.customer.application.service.dto.group.UpdateGroupDto

data class UpdateGroupRequest(
    val groupName: String?,
    val groupDescription: String?,
) {
    fun toServiceRequest(groupId: String): UpdateGroupDto {
        return UpdateGroupDto(
            groupId = groupId,
            groupName = this.groupName,
            groupDescription = this.groupDescription,
        )
    }
}
