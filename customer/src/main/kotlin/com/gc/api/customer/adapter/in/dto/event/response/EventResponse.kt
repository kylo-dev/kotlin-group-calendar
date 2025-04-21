package com.gc.api.customer.adapter.`in`.dto.event.response

import com.gc.api.customer.domain.model.EventAlarm
import com.gc.api.customer.domain.model.event.Event
import com.gc.api.customer.domain.model.event.EventFrequency
import com.gc.api.customer.domain.model.label.EventLabel
import java.time.LocalDateTime

data class EventResponse(
    val id: String,
    val title: String,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime,
    val isAllDay: Boolean,
    val alarm: EventAlarm,
    val frequency: EventFrequency,
    val label: EventLabel,
    val memberId: String,
) {

    companion object {
        fun toEventResponse(event: Event, label: EventLabel): EventResponse {
            return EventResponse(
                event.id,
                event.title,
                event.startDateTime,
                event.endDateTime,
                event.isAllDay,
                event.alarm,
                event.frequency,
                label,
                event.memberId
            )
        }
    }
}