package com.gc.api.customer.domain.model.event

import com.gc.api.customer.domain.model.EventAlarm
import java.awt.Label
import java.time.LocalDate
import java.time.LocalTime

data class Event(
  val id: String,
  val date: LocalDate,
  val startTime: LocalTime?,
  val endTime: LocalTime?,
  val isAllDay: Boolean,
  val label: Label,
  val alarm: EventAlarm,
  val memberId: String,
)