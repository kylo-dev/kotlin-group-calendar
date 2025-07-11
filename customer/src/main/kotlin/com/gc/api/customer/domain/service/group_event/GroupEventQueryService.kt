package com.gc.api.customer.domain.service.group_event

import com.gc.api.customer.application.port.out.persistence.group_event.GetGroupEventPort
import com.gc.api.customer.domain.model.group.GroupEvent
import org.springframework.stereotype.Service

@Service
class GroupEventQueryService(
    private val getGroupEventPort: GetGroupEventPort,
) {

    fun getTop3EventsByGroup(groupIds: List<String>): Map<String, List<GroupEvent>> {
        val groupEvents: List<GroupEvent> = getGroupEventPort.findTop3EventsByGroup(groupIds)
        return groupEvents.groupBy { it.groupId }
    }
}