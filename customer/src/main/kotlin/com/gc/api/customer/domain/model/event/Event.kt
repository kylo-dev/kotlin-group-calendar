package com.gc.api.customer.domain.model.event

import com.gc.api.customer.domain.model.EventAlarm

data class Event(
  val id: String,
  val title: String,
  val eventPeriod: EventPeriod,
  val isAllDay: Boolean,
  val alarm: EventAlarm,
  val frequency: EventFrequency,
  val labelId: String,
  val memberId: String,
)