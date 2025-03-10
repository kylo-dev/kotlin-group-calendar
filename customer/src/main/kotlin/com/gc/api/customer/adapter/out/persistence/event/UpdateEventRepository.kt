package com.gc.api.customer.adapter.out.persistence.event

import com.gc.api.customer.application.port.out.persistence.event.UpdateEventPort
import com.gc.api.customer.application.service.dto.event.UpdateEventDto
import com.gc.storage.document.event.EventDocument
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.data.mongodb.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class UpdateEventRepository(
  val operations: MongoOperations,
): QuerydslRepositorySupport(operations), UpdateEventPort{

  override fun updateEvent(request: UpdateEventDto) {

    val query = Query(Criteria.where("_id").`is`(request.id))

    val update = Update()
    request.title?.let { update.set("title", it) }
    request.date?.let { update.set("date", it) }
    request.startTime?.let { update.set("startTime", it) }
    request.endTime?.let { update.set("endTime", it) }
    request.isAllDay?.let { update.set("isAllDay", it) }
    request.alarm?.let { update.set("alarm", it) }
    request.labelId?.let { update.set("labelId", it) }
    request.frequency?.let { update.set("frequency", it) }

    if (update.updateObject.keys.isNotEmpty()) {
      update.set("updatedAt", LocalDateTime.now())
      operations.updateFirst(query, update, EventDocument::class.java)
    }
  }
}