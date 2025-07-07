package com.gc.api.customer.adapter.out.persistence.event

import com.gc.api.customer.application.port.out.persistence.event.GetEventPort
import com.gc.api.customer.application.service.dto.event.GetCalendarDto
import com.gc.api.customer.application.service.dto.event.SearchEventDto
import com.gc.api.customer.domain.model.EventAlarm
import com.gc.api.customer.domain.model.event.Event
import com.gc.api.customer.domain.model.event.EventFrequency
import com.gc.api.customer.domain.model.event.EventPeriod
import com.gc.storage.document.event.EventDocument
import com.gc.storage.document.event.EventMongoRepository
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.time.LocalTime

@Repository
class GetEventRepository(
    private val eventMongoRepository: EventMongoRepository,
    private val mongoTemplate: MongoTemplate,
) : GetEventPort {

    override fun getEvent(eventId: String): Event? {
        return eventMongoRepository.findByIdOrNull(eventId)
            ?.let { documentToModel(it) }
    }

    override fun getCalendar(getCalendarDto: GetCalendarDto): List<Event> {

        val startDateTime = getCalendarDto.startDate.atTime(LocalTime.MIN)
        val endDateTime = getCalendarDto.endDate.atTime(LocalTime.MAX)

        val query = Query().apply {
            addCriteria(
                Criteria.where("memberId").`is`(getCalendarDto.memberId)
                    .and("startDateTime").gte(startDateTime)
                    .and("endDateTime").lte(endDateTime)
            )
        }

        val results = mongoTemplate.find(query, EventDocument::class.java)

        return results.map { documentToModel(it) }
    }

    override fun searchEvents(request: SearchEventDto): List<Event> {

        val query = Query().apply {
            addCriteria(
                Criteria.where("memberId").`is`(request.memberId)
                    .and("title").regex(request.keyword, "i")
            )

            request.cursor?.let { cursor ->
                addCriteria(Criteria.where("_id").lt(cursor))
            }

            with(
                Sort.by(
                    Sort.Order.desc("_id"),
                    Sort.Order.desc("isAllDay"),
                    Sort.Order.asc("startDateTime"),
                    Sort.Order.asc("endDateTime")
                )
            )
            limit(request.size)
        }

//        logger.info {query.toString()}

        val results = mongoTemplate.find(query, EventDocument::class.java)
        return results.map { documentToModel(it) }
    }

    private fun documentToModel(document: EventDocument): Event {
        return Event(
            id = document.id!!,
            title = document.title,
            eventPeriod = EventPeriod(document.startDateTime, document.endDateTime),
            isAllDay = document.isAllDay,
            alarm = EventAlarm.from(document.alarm),
            frequency = EventFrequency.from(document.frequency),
            labelId = document.labelId,
            memberId = document.memberId
        )
    }
}