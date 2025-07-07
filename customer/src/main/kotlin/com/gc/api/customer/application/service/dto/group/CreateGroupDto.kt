package com.gc.api.customer.application.service.dto.group

import com.gc.api.customer.application.service.dto.group.member.JoinGroupMemberDto
import com.gc.api.customer.domain.model.group.Group

data class CreateGroupDto(
    val groupName: String,
    val groupProfile: String,
    val groupDescription: String,
    val memberId: String,
    val memberNickname: String,
    val memberProfile: String,
) {
    fun toJoinGroupDto(group: Group): JoinGroupMemberDto {
        return JoinGroupMemberDto(
            groupId = group.id,
            memberId = memberId,
            nickname = memberNickname,
            profile = memberProfile,
        )
    }
}