package com.gc.api.customer.application.service.dto.event

import com.gc.api.customer.domain.model.EventAlarm
import com.gc.api.customer.domain.model.event.EventFrequency
import java.time.LocalDateTime

data class PostEventDto(
  val title: String,
  val startDateTime: LocalDateTime,
  val endDateTime: LocalDateTime,
  val isAllDay: Boolean,
  val alarm: EventAlarm,
  val labelId: String,
  val frequency: EventFrequency,
  val memberId: String,
)
