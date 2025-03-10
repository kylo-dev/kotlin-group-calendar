package com.gc.api.customer.application.service.dto.event

import java.time.LocalDate
import java.time.LocalTime

data class UpdateEventDto(
  val id: String,
  val title: String?,
  val date: LocalDate?,
  val startTime: LocalTime?,
  val endTime: LocalTime?,
  val isAllDay: Boolean?,
  val alarm: String?,
  val labelId: String?,
  val frequency: String?,
)
