package com.gc.api.customer.application.service.facade.event

import com.gc.api.customer.adapter.`in`.dto.event.response.EventResponse
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
}