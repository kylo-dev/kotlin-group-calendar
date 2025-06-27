package com.gc.api.customer.application.port.out.persistence.event

import com.gc.api.customer.application.service.dto.event.GetCalendarDto
import com.gc.api.customer.domain.model.event.Event

interface GetEventPort {

  fun getEvent(eventId: String): Event
  fun getCalendar(getCalendarDto: GetCalendarDto): List<Event>
}