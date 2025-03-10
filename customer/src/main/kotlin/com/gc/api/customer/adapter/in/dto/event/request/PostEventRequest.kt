package com.gc.api.customer.adapter.`in`.dto.event.request

import com.gc.api.customer.application.service.dto.event.PostEventDto
import com.gc.api.customer.domain.model.EventAlarm
import com.gc.api.customer.domain.model.event.EventFrequency
import com.gc.api.customer.domain.model.member.Member
import java.time.LocalDateTime
import java.time.LocalTime

data class PostEventRequest(
  val title: String,
  val startDateTime: LocalDateTime,
  val endDateTime: LocalDateTime?,
  val isAllDay: Boolean,
  val alarm: EventAlarm?,
  val labelId: String,
  val frequency: EventFrequency?,
) {

  fun toEventServiceRequest(eventRequest: PostEventRequest, member: Member): PostEventDto {

    val (eventStartDateTime, eventEndDateTime) = if (eventRequest.isAllDay) {
      eventRequest.startDateTime.toLocalDate()
        .let { it.atTime(LocalTime.MIN) to it.atTime(LocalTime.MAX) }
    }
    else
      eventRequest.startDateTime to eventRequest.endDateTime

    return PostEventDto(
      eventRequest.title,
      eventStartDateTime,
      eventEndDateTime!!,
      eventRequest.isAllDay,
      eventRequest.alarm?:EventAlarm.NONE,
      eventRequest.labelId,
      eventRequest.frequency?:EventFrequency.NONE,
      member.id
    )
  }
}
