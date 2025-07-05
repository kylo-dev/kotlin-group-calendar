package com.gc.api.customer.domain.model.event

import java.time.LocalDateTime

data class EventPeriod(
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime,
)
