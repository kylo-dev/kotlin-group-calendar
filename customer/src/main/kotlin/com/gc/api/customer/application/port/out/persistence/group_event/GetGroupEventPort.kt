package com.gc.api.customer.application.port.out.persistence.group_event

import com.gc.api.customer.domain.model.group.GroupEvent

interface GetGroupEventPort {
    fun findTop3EventsByGroup(groupIds: List<String>): List<GroupEvent>
}