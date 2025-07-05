package com.gc.api.customer.adapter.`in`.dto.response.group

import com.gc.api.customer.domain.model.group.Group

data class GroupResponse(
    val groupName: String,
    val groupProfile: String,
    val groupLink: String,
) {
    companion object {
        fun toGroupResponse(group: Group): GroupResponse {
            return GroupResponse(
                groupName = group.name,
                groupProfile = group.profile,
                groupLink = group.link,
            )
        }
    }
}
