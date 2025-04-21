package com.gc.api.customer.domain.model.event

import com.gc.api.customer.domain.model.EventAlarm
import java.time.LocalDateTime

data class Event(
  val id: String,
  val title: String,
  val startDateTime: LocalDateTime,
  val endDateTime: LocalDateTime,
  val isAllDay: Boolean,
  val alarm: EventAlarm,
  val frequency: EventFrequency,
  val labelId: String,
  val memberId: String,
)