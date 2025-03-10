package com.gc.api.customer.application.port.out.persistence.event

import com.gc.api.customer.application.service.dto.event.PostEventDto

interface PostEventPort {
  fun saveEvent(postEventDto: PostEventDto): String
}