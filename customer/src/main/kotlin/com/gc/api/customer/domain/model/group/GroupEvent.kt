package com.gc.api.customer.domain.model.group

import com.gc.api.customer.domain.model.event.EventPeriod

data class GroupEvent(
    val groupId: String,
    val groupEventId: String,
    val eventTitle: String,
    val eventPeriod: EventPeriod,
)
