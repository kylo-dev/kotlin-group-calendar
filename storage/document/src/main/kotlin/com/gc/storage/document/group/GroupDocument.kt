package com.gc.storage.document.group

import com.gc.storage.document.common.BaseTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("groups")
@Entity
data class GroupDocument(
  val name: String,
  val profile: String,
  val description: String,
  val link: String,
  @Id
  val id: String? = null,
): BaseTimeEntity()
