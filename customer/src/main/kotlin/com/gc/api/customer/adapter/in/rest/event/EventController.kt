package com.gc.api.customer.adapter.`in`.rest.event

import com.gc.api.customer.adapter.`in`.dto.request.event.PostEventRequest
import com.gc.api.customer.adapter.`in`.dto.request.event.UpdateEventRequest
import com.gc.api.customer.adapter.`in`.dto.response.ResponseData
import com.gc.api.customer.adapter.`in`.dto.response.event.CalendarResponse
import com.gc.api.customer.adapter.`in`.dto.response.event.EventResponse
import com.gc.api.customer.application.service.dto.event.GetCalendarDto
import com.gc.api.customer.application.service.dto.event.SearchEventDto
import com.gc.api.customer.application.service.facade.event.EventFacade
import com.gc.api.customer.domain.service.event.EventCommandService
import com.gc.api.customer.framework.annotation.RequestInfo
import com.gc.api.customer.framework.annotation.RequireAuth
import com.gc.api.customer.utils.UrlConstant
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping(UrlConstant.EVENT)
class EventController(
    private val eventFacade: EventFacade,
    private val eventCommandService: EventCommandService,
    private val requestInfo: RequestInfo,
) {

    @GetMapping("/{eventId}")
    fun getEvent(@PathVariable eventId: String): ResponseData<EventResponse> {
        return ResponseData.success(eventFacade.getEvent(eventId))
    }

    @GetMapping
    fun getEvents(
        @RequestParam startDate: LocalDate,
        @RequestParam endDate: LocalDate?,
    ): ResponseData<List<CalendarResponse>> {
        val getCalenderDto = GetCalendarDto.of(requestInfo.member, startDate, endDate)
        val calendarEvents = eventFacade.getCalendar(getCalenderDto)
        return ResponseData.success(CalendarResponse.toCalendarResponse(calendarEvents))
    }

    /**
     * Cursor 기반 페이징
     */
    @GetMapping("/search")
    @RequireAuth
    fun searchEvents(
        @RequestParam q: String,
        @RequestParam cursor: String?,
        @RequestParam(defaultValue = "10") size: Int,
    ): ResponseData<List<CalendarResponse>> {
        val searchEvents =
            eventFacade.searchEvents(SearchEventDto.of(requestInfo.member, q, cursor, size))
        return ResponseData.success(CalendarResponse.toCalendarResponse(searchEvents))
    }


    @PostMapping
    @RequireAuth
    fun saveEvent(@RequestBody postEventRequest: PostEventRequest): ResponseData<String> {
        val eventServiceRequest =
            postEventRequest.toEventServiceRequest(postEventRequest, requestInfo.member)
        return ResponseData.success(eventCommandService.addEvent(eventServiceRequest))
    }

    @PatchMapping("/{eventId}")
    @RequireAuth
    fun updateEvent(
        @PathVariable eventId: String,
        @RequestBody updateEventRequest: UpdateEventRequest,
    ): ResponseData<String> {
        eventCommandService.updateEvent(updateEventRequest.toServiceRequest(eventId))
        return ResponseData.success(eventId)
    }

    @DeleteMapping("/{eventId}")
    @RequireAuth
    fun deleteEvent(@PathVariable eventId: String): ResponseData<String> {
        eventCommandService.deleteEvent(eventId)
        return ResponseData.success(eventId)
    }
}