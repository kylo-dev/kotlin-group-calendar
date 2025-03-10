package com.gc.api.customer.application.port.out.persistence.event

import com.gc.api.customer.application.service.dto.event.EventServiceRequest

interface PostEventPort {
  fun saveEvent(eventServiceRequest: EventServiceRequest): String
}