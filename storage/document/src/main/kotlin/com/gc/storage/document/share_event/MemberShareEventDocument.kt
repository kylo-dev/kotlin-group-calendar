package com.gc.storage.document.share_event

import com.gc.storage.document.common.BaseTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("member_share_events")
@Entity
data class MemberShareEventDocument(
  val eventId: String,
  val groupPromiseId: String,
  @Id
  val id: String? = null,
): BaseTimeEntity()
