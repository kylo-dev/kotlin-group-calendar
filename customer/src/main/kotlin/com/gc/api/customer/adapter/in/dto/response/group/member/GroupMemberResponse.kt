package com.gc.api.customer.adapter.`in`.dto.response.group.member

import com.gc.api.customer.domain.model.group.GroupMember

data class GroupMemberResponse(
    val memberId: String,
    val memberNickname: String,
    val memberProfile: String?,
) {
    companion object {
        fun from(groupMember: GroupMember): GroupMemberResponse {
            return GroupMemberResponse(
                memberId = groupMember.memberId,
                memberNickname = groupMember.memberNickname,
                memberProfile = groupMember.memberProfile,
            )
        }
    }
}
