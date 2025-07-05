package com.gc.api.customer.adapter.out.persistence.event

import com.gc.api.customer.application.port.out.persistence.event.DeleteEventPort
import com.gc.storage.document.event.EventMongoRepository
import org.springframework.stereotype.Repository

@Repository
class DeleteEventRepository(
    private val eventMongoRepository: EventMongoRepository,
) : DeleteEventPort {

    override fun deleteEvent(eventId: String) {
        eventMongoRepository.deleteById(eventId)
    }
}