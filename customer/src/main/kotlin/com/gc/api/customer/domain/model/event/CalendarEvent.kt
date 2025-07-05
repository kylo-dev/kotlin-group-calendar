package com.gc.api.customer.domain.model.event

import com.gc.api.customer.domain.model.label.EventLabel

data class CalendarEvent(
    val eventId: String,
    val title: String,
    val eventPeriod: EventPeriod,
    val labelId: String,
    val labelColor: String,
) {
    companion object {
        fun loadFromDocument(event: Event, eventLabel: EventLabel): CalendarEvent {
            return CalendarEvent(
                eventId = event.id,
                title = event.title,
                eventPeriod = event.eventPeriod,
                labelId = event.labelId,
                labelColor = eventLabel.color,
            )
        }
    }
}
