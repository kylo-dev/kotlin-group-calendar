package com.gc.api.customer.domain.model.event

import com.fasterxml.jackson.annotation.JsonCreator

enum class EventFrequency {
  NONE,
  DAILY,
  WEEKLY,
  MONTHLY,
  YEARLY,
  ;

  companion object {
    @JsonCreator
    fun from(value: String): EventFrequency {
      return entries.find {it.name == value}
        ?: throw IllegalArgumentException("Invalid EventFrequency value $value")
    }
  }
}