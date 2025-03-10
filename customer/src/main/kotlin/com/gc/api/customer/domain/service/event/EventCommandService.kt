package com.gc.api.customer.domain.service.event

import com.gc.api.customer.application.port.out.persistence.event.DeleteEventPort
import com.gc.api.customer.application.port.out.persistence.event.PostEventPort
import com.gc.api.customer.application.port.out.persistence.event.UpdateEventPort
import com.gc.api.customer.application.service.dto.event.PostEventDto
import com.gc.api.customer.application.service.dto.event.UpdateEventDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class EventCommandService(
  private val postEventPort: PostEventPort,
  private val updateEventPort: UpdateEventPort,
  private val deleteEventPort: DeleteEventPort,
) {

  fun addEvent(postEventDto: PostEventDto): String {
    return postEventPort.saveEvent(postEventDto)
  }

  fun updateEvent(updateEventDto: UpdateEventDto) {
    updateEventPort.updateEvent(updateEventDto)
  }

  fun deleteEvent(eventId: String) {
    deleteEventPort.deleteEvent(eventId)
  }
}