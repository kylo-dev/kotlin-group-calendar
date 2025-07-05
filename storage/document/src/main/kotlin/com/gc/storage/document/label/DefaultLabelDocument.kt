package com.gc.storage.document.label

import com.gc.storage.document.common.BaseTimeEntity
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("default_labels")
data class DefaultLabelDocument(
  val label: String,
  val color: String,
  @Id
  val id: String? = null,
): BaseTimeEntity() {

}
