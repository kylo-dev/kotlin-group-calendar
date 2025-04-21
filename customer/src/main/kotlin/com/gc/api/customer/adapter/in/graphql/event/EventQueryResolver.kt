package com.gc.api.customer.adapter.`in`.graphql.event

import com.gc.api.customer.adapter.`in`.dto.event.request.response.EventResponse
import com.gc.api.customer.application.service.facade.event.EventFacade
import com.gc.api.customer.framework.annotation.RequestInfo
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class EventQueryResolver(
  private val eventFacade: EventFacade,
  private val requestInfo: RequestInfo,
) {

  @QueryMapping
  fun event(@Argument eventId: String): EventResponse {
    return eventFacade.getEvent(eventId)
  }
}