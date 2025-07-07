package com.gc.api.customer.adapter.`in`.dto.request.group

import com.gc.api.customer.application.service.dto.group.CreateGroupDto
import com.gc.api.customer.domain.model.member.Member

data class CreateGroupRequest(
    val groupName: String,
    val groupProfile: String,
    val groupDescription: String,
    val memberNickname: String,
    val memberProfile: String,
) {
    fun toServiceRequest(member: Member): CreateGroupDto {
        return CreateGroupDto(
            this.groupName,
            this.groupProfile,
            this.groupDescription,
            member.id,
            this.memberNickname,
            this.memberProfile,
        )
    }
}