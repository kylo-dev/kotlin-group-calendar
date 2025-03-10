package com.gc.api.customer.domain.model

import com.fasterxml.jackson.annotation.JsonCreator

enum class EventAlarm {
  NONE,
  D_DAY,
  D_7DAY,
  BEFORE_10,
  BEFORE_30,
  BEFORE_60,
  ;

  companion object {
    @JsonCreator
    fun from(value: String): EventAlarm {
      return entries.find { it.name == value }
        ?: throw IllegalArgumentException("Invalid EventAlarm value $value")
    }
  }
}