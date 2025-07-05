package com.gc.storage.document.group

import com.gc.storage.document.common.BaseTimeEntity
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("groups")
data class GroupDocument(
  val name: String,
  val profile: String,
  val description: String,
  val link: String,
  @Id
  val id: String? = null,
): BaseTimeEntity()
