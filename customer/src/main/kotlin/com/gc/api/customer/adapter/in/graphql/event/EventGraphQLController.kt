package com.gc.api.customer.adapter.`in`.graphql.event

import com.gc.api.customer.adapter.`in`.dto.event.request.PostEventRequest
import com.gc.api.customer.domain.service.event.EventCommandService
import com.gc.api.customer.framework.annotation.RequestInfo
import com.gc.api.customer.framework.annotation.RequireAuth
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller

@Controller
class EventGraphQLController(
  private val eventCommandService: EventCommandService,
  private val requestInfo: RequestInfo
) {

  @MutationMapping
  @RequireAuth
  fun saveEvent(@Argument postEventRequest: PostEventRequest): String {

    val eventServiceRequest =
      postEventRequest.toEventServiceRequest(postEventRequest, requestInfo.member)
    return eventCommandService.addEvent(eventServiceRequest)
  }
}