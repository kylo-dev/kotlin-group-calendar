package com.gc.api.customer.application.service.dto.event

import java.time.LocalDateTime

data class UpdateEventDto(
  val id: String,
  val title: String?,
  val startDateTime: LocalDateTime?,
  val endDateTime: LocalDateTime?,
  val isAllDay: Boolean?,
  val alarm: String?,
  val labelId: String?,
  val frequency: String?,
)
