package com.gc.api.customer.domain.model.event

import com.gc.api.customer.domain.model.label.EventLabel
import java.time.LocalDate

data class CalendarEvent(
    val eventId: String,
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val labelId: String,
    val labelColor: String,
) {
    companion object {
        fun loadFromDocument(event: Event, eventLabel: EventLabel): CalendarEvent {
            return CalendarEvent(
                eventId = event.id,
                title = event.title,
                startDate = event.startDateTime.toLocalDate(),
                endDate = event.endDateTime.toLocalDate(),
                labelId = event.labelId,
                labelColor = eventLabel.color,
            )
        }
    }
}
