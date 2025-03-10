package com.gc.api.customer.adapter.out.persistence.event

import com.gc.api.customer.application.port.out.persistence.event.PostEventPort
import com.gc.api.customer.application.service.dto.event.EventServiceRequest
import com.gc.storage.document.event.EventDocument
import com.gc.storage.document.event.EventMongoRepository
import org.springframework.stereotype.Repository

@Repository
class PostEventRepository(
  val eventMongoRepository: EventMongoRepository,
): PostEventPort {

  override fun saveEvent(eventServiceRequest: EventServiceRequest): String {
    val newEvent = eventMongoRepository.save(createEvent(eventServiceRequest))
    return newEvent.id!!
  }

  private fun createEvent(request: EventServiceRequest): EventDocument {
    return EventDocument(
      title = request.title,
      date = request.date,
      startTime = request.startTime,
      endTime = request.endTime,
      isAllDay = request.isAllDay,
      labelId = request.labelId,
      alarm = request.alarm.name,
      memberId = request.memberId
    )
  }
}