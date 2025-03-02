package com.gc.storage.document.group_event

import com.gc.storage.document.common.BaseTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import java.time.LocalTime

@Document("group_events")
@Entity
data class GroupEventDocument(
  val groupPromiseId: String,
  val isAllDay: Boolean,
  val date: LocalDate,
  val startTime: LocalTime?,
  val endTime: LocalTime?,
  @Id
  val id: String? = null,
): BaseTimeEntity()
