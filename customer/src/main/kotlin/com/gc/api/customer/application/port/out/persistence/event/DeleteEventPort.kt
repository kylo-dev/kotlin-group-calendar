package com.gc.api.customer.application.port.out.persistence.event

interface DeleteEventPort {
  fun deleteEvent(eventId: String)
}