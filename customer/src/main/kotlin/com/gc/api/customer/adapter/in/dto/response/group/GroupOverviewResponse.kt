package com.gc.api.customer.adapter.`in`.dto.response.group

import com.gc.api.customer.adapter.`in`.dto.response.group.member.GroupMemberResponse
import com.gc.api.customer.domain.model.group.Group
import com.gc.api.customer.domain.model.group.GroupEvent
import com.gc.api.customer.domain.model.group.GroupOverview
import java.time.LocalDateTime

data class GroupOverViewResponse(
    val groupData: GroupData,
    val memberData: List<GroupMemberResponse>,
) {
    companion object {
        fun from(groupOverviews: List<GroupOverview>): List<GroupOverViewResponse> {
            return groupOverviews.map {
                GroupOverViewResponse(
                    groupData = GroupData.from(it.group, it.groupEvents.map(GroupEventResponse::from)),
                    memberData = it.members.map(GroupMemberResponse::from)
                )
            }
        }
    }
}

data class GroupData(
    val groupId: String,
    val groupName: String,
    val groupProfile: String,
    val groupEvents: List<GroupEventResponse>,
) {
    companion object {
        fun from(group: Group, groupEvents: List<GroupEventResponse>): GroupData {
            return GroupData(
                groupId = group.id,
                groupName = group.name,
                groupProfile = group.profile,
                groupEvents = groupEvents
            )
        }
    }
}

data class GroupEventResponse(
    val title: String,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime,
) {
    companion object {
        fun from(groupEvent: GroupEvent): GroupEventResponse {
            return GroupEventResponse(
                title = groupEvent.eventTitle,
                startDateTime = groupEvent.eventPeriod.startDateTime,
                endDateTime = groupEvent.eventPeriod.endDateTime,
            )
        }
    }
}