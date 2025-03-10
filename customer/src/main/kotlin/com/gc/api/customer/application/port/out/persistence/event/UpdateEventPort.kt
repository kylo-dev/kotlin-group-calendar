package com.gc.api.customer.application.port.out.persistence.event

import com.gc.api.customer.application.service.dto.event.UpdateEventDto

interface UpdateEventPort {

  fun updateEvent(updateEventDto: UpdateEventDto)
}