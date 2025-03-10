package com.gc.api.customer.adapter.`in`.dto.event.request

import com.gc.api.customer.application.service.dto.event.EventServiceRequest
import com.gc.api.customer.domain.model.EventAlarm
import com.gc.api.customer.domain.model.member.Member
import java.time.LocalDate
import java.time.LocalTime

data class PostEventRequest(
  val title: String,
  val date: LocalDate,
  val startTime: LocalTime?,
  val endTime: LocalTime?,
  val isAllDay: Boolean,
  val alarm: EventAlarm,
  val labelId: String,
) {

  fun toEventServiceRequest(eventRequest: PostEventRequest, member: Member): EventServiceRequest {
    return EventServiceRequest(
      eventRequest.title,
      eventRequest.date,
      eventRequest.startTime,
      eventRequest.endTime,
      eventRequest.isAllDay,
      eventRequest.alarm,
      eventRequest.labelId,
      member.id
    )
  }
}
