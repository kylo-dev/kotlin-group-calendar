package com.gc.api.customer.adapter.`in`.dto.request.event

import com.gc.api.customer.application.service.dto.event.UpdateEventDto
import com.gc.api.customer.domain.model.EventAlarm
import com.gc.api.customer.domain.model.event.EventFrequency
import java.time.LocalDateTime
import java.time.LocalTime

data class UpdateEventRequest(
    val title: String?,
    val startDateTime: LocalDateTime?,
    val endDateTime: LocalDateTime?,
    val isAllDay: Boolean?,
    val alarm: EventAlarm?,
    val labelId: String?,
    val frequency: EventFrequency?
) {

    fun toServiceRequest(eventId: String): UpdateEventDto {

        val (eventStartDateTime, eventEndDateTime) = if (this.isAllDay == true) {
            this.startDateTime?.toLocalDate()
                ?.let { it.atTime(LocalTime.MIN) to it.atTime(LocalTime.MAX) }
                ?: (null to null)
        } else
            this.startDateTime to this.endDateTime

        return UpdateEventDto(
            id = eventId,
            title = this.title,
            startDateTime = eventStartDateTime,
            endDateTime = eventEndDateTime,
            isAllDay = this.isAllDay,
            alarm = this.alarm?.name,
            labelId = this.labelId,
            frequency = this.frequency?.name,
        )
    }
}
