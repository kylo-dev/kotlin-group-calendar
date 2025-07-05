//package com.gc.api.customer.adapter.`in`.graphql.event
//
//import com.gc.api.customer.adapter.`in`.dto.response.event.CalendarResponse
//import com.gc.api.customer.adapter.`in`.dto.response.event.EventResponse
//import com.gc.api.customer.application.service.dto.event.GetCalendarDto
//import com.gc.api.customer.application.service.facade.event.EventFacade
//import com.gc.api.customer.framework.annotation.RequestInfo
//import org.springframework.graphql.data.method.annotation.Argument
//import org.springframework.graphql.data.method.annotation.QueryMapping
//import org.springframework.stereotype.Controller
//import java.time.LocalDate
//
//@Controller
//class EventQueryResolver(
//    private val eventFacade: EventFacade,
//    private val requestInfo: RequestInfo,
//) {
//
//    @QueryMapping
//    fun event(@Argument eventId: String): EventResponse {
//        return eventFacade.getEvent(eventId)
//    }
//
//    @QueryMapping
//    fun events(
//        @Argument startDate: LocalDate,
//        @Argument endDate: LocalDate?
//    ): List<CalendarResponse> {
//
//        val getCalendarDto = GetCalendarDto.of(requestInfo.member.id, startDate, endDate)
//        val calendarEvents = eventFacade.getCalendar(getCalendarDto)
//
//        return CalendarResponse.toCalendarResponse(calendarEvents)
//    }
//}