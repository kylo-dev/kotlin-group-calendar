package com.gc.api.customer.domain.model.group

data class GroupOverview(
    val group: Group,
    val members: List<GroupMember>,
    val groupEvents: List<GroupEvent>,
) {
    companion object {
        fun of(group: Group, members: List<GroupMember>, groupEvents: List<GroupEvent>): GroupOverview {
            return GroupOverview(
                group,
                members,
                groupEvents,
            )
        }
    }
}
