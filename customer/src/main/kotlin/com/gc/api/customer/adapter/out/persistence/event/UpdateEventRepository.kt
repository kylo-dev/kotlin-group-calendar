package com.gc.api.customer.adapter.out.persistence.event

import com.gc.api.customer.application.port.out.persistence.event.UpdateEventPort
import com.gc.api.customer.application.service.dto.event.UpdateEventDto
import com.gc.storage.document.event.EventDocument
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class UpdateEventRepository(
    private val mongoTemplate: MongoTemplate,
) : UpdateEventPort {

    override fun updateEvent(request: UpdateEventDto) {

        val query = Query(Criteria.where("_id").`is`(request.id))

        val update = Update()
        request.title?.let { update.set("title", it) }
        request.startDateTime?.let { update.set("startDateTime", it) }
        request.endDateTime?.let { update.set("endDateTime", it) }
        request.isAllDay?.let { update.set("isAllDay", it) }
        request.alarm?.let { update.set("alarm", it) }
        request.labelId?.let { update.set("labelId", it) }
        request.frequency?.let { update.set("frequency", it) }

        if (update.updateObject.keys.isNotEmpty()) {
            update.set("updatedAt", LocalDateTime.now())
            mongoTemplate.updateFirst(query, update, EventDocument::class.java)
        }
    }
}