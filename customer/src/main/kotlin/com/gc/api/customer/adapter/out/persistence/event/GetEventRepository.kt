package com.gc.api.customer.adapter.out.persistence.event

import com.gc.api.customer.application.port.out.persistence.event.GetEventPort
import com.gc.api.customer.application.service.dto.event.GetCalendarDto
import com.gc.api.customer.domain.model.EventAlarm
import com.gc.api.customer.domain.model.event.Event
import com.gc.api.customer.domain.model.event.EventFrequency
import com.gc.storage.document.event.EventDocument
import com.gc.storage.document.event.EventMongoRepository
import com.gc.storage.document.event.QEventDocument.eventDocument
import common.exception.CustomNotFoundException
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository
import java.time.LocalTime

@Repository
class GetEventRepository(
  private val eventMongoRepository: EventMongoRepository,
  val operations: MongoOperations,
): GetEventPort, QuerydslRepositorySupport(operations) {

  override fun getEvent(eventId: String): Event {
    return eventMongoRepository.findById(eventId)
      .orElseThrow { CustomNotFoundException("일정을 찾을 수 없습니다.") }
      .let { documentToModel(it) }
  }

  override fun getCalendar(getCalendarDto: GetCalendarDto): List<Event> {

    val startDateTime = getCalendarDto.startDate.atTime(LocalTime.MIN)
    val endDateTime = getCalendarDto.endDate.atTime(LocalTime.MAX)

    val results = from(eventDocument)
      .where(
        eventDocument.memberId.eq(getCalendarDto.memberId)
          .and(eventDocument.startDateTime.goe(startDateTime))
          .and(eventDocument.endDateTime.loe(endDateTime))
      )
      .fetch()

    return results.stream()
      .map { documentToModel(it) }
      .toList()

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