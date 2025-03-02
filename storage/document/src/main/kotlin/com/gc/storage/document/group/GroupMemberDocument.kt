package com.gc.storage.document.group

import com.gc.storage.document.common.BaseTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("group_members")
@Entity
data class GroupMemberDocument(
  val memberId: String,
  val nickname: String,
  val profile: String?,
  val groupId: String,
  @Id
  val id: String? = null,
): BaseTimeEntity()
