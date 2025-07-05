package com.gc.storage.document.share_event

import com.gc.storage.document.common.BaseTimeEntity
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("member_share_events")
data class MemberShareEventDocument(
  val eventId: String,
  val groupPromiseId: String,
  @Id
  val id: String? = null,
): BaseTimeEntity()
