package com.gc.storage.document.event

import com.gc.storage.document.common.BaseTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("recurrence_events")
@Entity
data class RecurrenceEventDocument(
  val eventId: String,
  val frequency: String,
  @Id
  val id: String? = null,
  ): BaseTimeEntity() {

}
