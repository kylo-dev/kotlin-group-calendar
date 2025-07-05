//package com.gc.api.customer.adapter.`in`.graphql.event
//
//import com.gc.api.customer.adapter.`in`.dto.request.event.PostEventRequest
//import com.gc.api.customer.adapter.`in`.dto.request.event.UpdateEventRequest
//import com.gc.api.customer.domain.service.event.EventCommandService
//import com.gc.api.customer.framework.annotation.RequestInfo
//import com.gc.api.customer.framework.annotation.RequireAuth
//import org.springframework.graphql.data.method.annotation.Argument
//import org.springframework.graphql.data.method.annotation.MutationMapping
//import org.springframework.stereotype.Controller
//
//@Controller
//class EventMutationResolver(
//  private val eventCommandService: EventCommandService,
//  private val requestInfo: RequestInfo,
//) {
//
//  @MutationMapping
//  @RequireAuth
//  fun saveEvent(@Argument postEventRequest: PostEventRequest): String {
//
//    val eventServiceRequest =
//      postEventRequest.toEventServiceRequest(postEventRequest, requestInfo.member)
//    return eventCommandService.addEvent(eventServiceRequest)
//  }
//
//  @MutationMapping
//  @RequireAuth
//  fun updateEvent(@Argument eventId: String, @Argument updateEventRequest: UpdateEventRequest): String {
//
//    eventCommandService.updateEvent(updateEventRequest.toServiceRequest(eventId))
//    return eventId
//  }
//
//  @MutationMapping
//  @RequireAuth
//  fun deleteEvent(@Argument eventId: String): String {
//    eventCommandService.deleteEvent(eventId)
//    return eventId
//  }
//}