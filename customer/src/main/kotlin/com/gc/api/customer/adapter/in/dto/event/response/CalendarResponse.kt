package com.gc.api.customer.adapter.`in`.dto.event.response

import com.gc.api.customer.domain.model.event.CalendarEvent
import java.time.LocalDate

data class CalendarResponse(
    val id: String,
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val labelColor: String,
) {
    companion object {
        fun toCalendarResponse(calendarEvents: List<CalendarEvent>): List<CalendarResponse> {
            return calendarEvents.map { toCalendarItem(it) }
                .toList()
        }

        private fun toCalendarItem(calendarEvent: CalendarEvent): CalendarResponse {
            return CalendarResponse(
                id = calendarEvent.eventId,
                title = calendarEvent.title,
                startDate = calendarEvent.startDate,
                endDate = calendarEvent.endDate,
                labelColor = calendarEvent.labelColor
            )
        }
    }
}
