package com.gc.api.customer.domain.service.event

import com.gc.api.customer.application.port.out.persistence.event.GetEventPort
import com.gc.api.customer.application.service.dto.event.GetCalendarDto
import com.gc.api.customer.domain.model.event.Event
import org.springframework.stereotype.Service

@Service
class EventQueryService(
  private val getEventPort: GetEventPort
) {

  fun getEvent(eventId: String): Event {
    return getEventPort.getEvent(eventId)
  }

  fun getCalendar(getCalendarDto: GetCalendarDto): List<Event> {
    return getEventPort.getCalendar(getCalendarDto)
  }
}