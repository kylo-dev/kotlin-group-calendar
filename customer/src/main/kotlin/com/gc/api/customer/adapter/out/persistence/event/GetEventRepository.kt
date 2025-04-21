package com.gc.api.customer.adapter.out.persistence.event

import com.gc.api.customer.application.port.out.persistence.event.GetEventPort
import com.gc.api.customer.domain.model.EventAlarm
import com.gc.api.customer.domain.model.event.Event
import com.gc.api.customer.domain.model.event.EventFrequency
import com.gc.storage.document.event.EventDocument
import com.gc.storage.document.event.EventMongoRepository
import common.exception.CustomNotFoundException
import org.springframework.stereotype.Repository

@Repository
class GetEventRepository(
  private val eventMongoRepository: EventMongoRepository,
): GetEventPort {

  override fun getEvent(eventId: String): Event {
    return eventMongoRepository.findById(eventId)
      .orElseThrow { CustomNotFoundException("일정을 찾을 수 없습니다.") }
      .let { documentToModel(it) }
  }

  private fun documentToModel(document: EventDocument): Event {
    return Event(
      id = document.id!!,
      title = document.title,
      startDateTime = document.startDateTime,
      endDateTime = document.endDateTime,
      isAllDay = document.isAllDay,
      alarm = EventAlarm.from(document.alarm),
      frequency = EventFrequency.from(document.frequency),
      labelId = document.labelId,
      memberId = document.memberId
    )
  }
}