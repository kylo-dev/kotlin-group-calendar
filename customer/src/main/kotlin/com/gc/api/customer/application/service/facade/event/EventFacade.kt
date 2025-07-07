package com.gc.api.customer.application.service.facade.event

import com.gc.api.customer.adapter.`in`.dto.response.event.EventResponse
import com.gc.api.customer.application.service.dto.event.GetCalendarDto
import com.gc.api.customer.application.service.dto.event.SearchEventDto
import com.gc.api.customer.domain.model.event.CalendarEvent
import com.gc.api.customer.domain.service.event.EventQueryService
import com.gc.api.customer.domain.service.label.LabelQueryService
import org.springframework.stereotype.Service

@Service
class EventFacade(
    private val eventQueryService: EventQueryService,
    private val labelQueryService: LabelQueryService,
) {

    fun getEvent(eventId: String): EventResponse {

        // event
        val event = eventQueryService.getEvent(eventId)

        // label
        val eventLabel = labelQueryService.getLabel(event.memberId, event.labelId)

        return EventResponse.toEventResponse(event, eventLabel)
    }

    fun getCalendar(getCalendarDto: GetCalendarDto): List<CalendarEvent> {

        // event
        val events = eventQueryService.getCalendar(getCalendarDto)
        val labelIds = events.map { it.labelId }.toSet()

        // label
        val allEventLabel = labelQueryService.getAllEventLabel(getCalendarDto.memberId, labelIds)
        val labelMap = allEventLabel.associateBy { it.id }

        // Calendar
        return events.map { event ->
            val label = labelMap[event.labelId]
            CalendarEvent.loadFromDocument(event, label!!)
        }.toList()
    }

    fun searchEvents(searchEventDto: SearchEventDto): List<CalendarEvent> {

        // search event
        val events = eventQueryService.searchEvents(searchEventDto)
        val labelIds = events.map { it.labelId }.toSet()

        // label
        val allEventLabel = labelQueryService.getAllEventLabel(searchEventDto.memberId, labelIds)
        val labelMap = allEventLabel.associateBy { it.id }

        // Calendar
        return events.map { event ->
            val label = labelMap[event.labelId]
            CalendarEvent.loadFromDocument(event, label!!)
        }
    }
}