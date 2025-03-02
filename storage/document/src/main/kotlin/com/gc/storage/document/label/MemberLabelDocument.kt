package com.gc.storage.document.label

import com.gc.storage.document.common.BaseTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("member_labels")
@Entity
data class MemberLabelDocument(
  val memberId: String,
  val labelId: String,
  val label: String,
  val color: String,
  @Id
  val id: String? = null,
): BaseTimeEntity()
