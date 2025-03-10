package com.gc.api.customer.adapter.`in`.dto.event.request

import com.gc.api.customer.application.service.dto.event.PostEventDto
import com.gc.api.customer.domain.model.EventAlarm
import com.gc.api.customer.domain.model.event.EventFrequency
import com.gc.api.customer.domain.model.member.Member
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class PostEventRequest(
  val title: String,
  val date: LocalDate,
  val startTime: LocalTime?,
  val endTime: LocalTime?,
  val isAllDay: Boolean,
  val alarm: EventAlarm?,
  val labelId: String,
  val frequency: EventFrequency?,
) {

  fun toEventServiceRequest(eventRequest: PostEventRequest, member: Member): PostEventDto {

    val eventStartDateTime = if (eventRequest.isAllDay)
      eventRequest.date.atTime(LocalTime.MIN)
    else
      LocalDateTime.of(eventRequest.date, eventRequest.startTime)

    val eventEndDateTime = if (eventRequest.isAllDay)
      eventRequest.date.atTime(LocalTime.MAX)
    else
      LocalDateTime.of(eventRequest.date, eventRequest.endTime)

    return PostEventDto(
      eventRequest.title,
      eventStartDateTime,
      eventEndDateTime,
      eventRequest.isAllDay,
      eventRequest.alarm?:EventAlarm.NONE,
      eventRequest.labelId,
      eventRequest.frequency?:EventFrequency.NONE,
      member.id
    )
  }
}
