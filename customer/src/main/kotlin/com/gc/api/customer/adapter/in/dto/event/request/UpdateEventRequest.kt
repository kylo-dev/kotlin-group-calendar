package com.gc.api.customer.adapter.`in`.dto.event.request

import com.gc.api.customer.application.service.dto.event.UpdateEventDto
import com.gc.api.customer.domain.model.EventAlarm
import com.gc.api.customer.domain.model.event.EventFrequency
import java.time.LocalDate
import java.time.LocalTime

data class UpdateEventRequest(
  val title: String?,
  val date: LocalDate?,
  val startTime: LocalTime?,
  val endTime: LocalTime?,
  val isAllDay: Boolean?,
  val alarm: EventAlarm?,
  val labelId: String?,
  val frequency: EventFrequency?
) {

  fun toServiceRequest(eventId: String): UpdateEventDto {
    return UpdateEventDto(
      id = eventId,
      title = this.title,
      date = this.date,
      startTime = this.startTime,
      endTime = this.endTime,
      isAllDay = this.isAllDay,
      alarm = this.alarm?.name,
      labelId = this.labelId,
      frequency = this.frequency?.name,
    )
  }
}
