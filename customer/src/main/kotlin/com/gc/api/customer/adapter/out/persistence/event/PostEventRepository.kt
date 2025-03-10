package com.gc.api.customer.adapter.out.persistence.event

import com.gc.api.customer.application.port.out.persistence.event.PostEventPort
import com.gc.api.customer.application.service.dto.event.PostEventDto
import com.gc.storage.document.event.EventDocument
import com.gc.storage.document.event.EventMongoRepository
import org.springframework.stereotype.Repository

@Repository
class PostEventRepository(
  val eventMongoRepository: EventMongoRepository,
): PostEventPort {

  override fun saveEvent(postEventDto: PostEventDto): String {
    val newEvent = eventMongoRepository.save(createEvent(postEventDto))
    return newEvent.id!!
  }

  private fun createEvent(request: PostEventDto): EventDocument {
    return EventDocument(
      title = request.title,
      startDateTime = request.startDateTime,
      endDateTime = request.endDateTime,
      isAllDay = request.isAllDay,
      labelId = request.labelId,
      alarm = request.alarm.name,
      frequency = request.frequency.name,
      memberId = request.memberId
    )
  }
}