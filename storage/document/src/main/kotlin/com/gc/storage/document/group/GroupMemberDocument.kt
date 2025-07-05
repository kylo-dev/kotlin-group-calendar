package com.gc.storage.document.group

import com.gc.storage.document.common.BaseTimeEntity
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("group_members")
data class GroupMemberDocument(
  val memberId: String,
  val nickname: String,
  val profile: String?,
  val groupId: String,
  @Id
  val id: String? = null,
): BaseTimeEntity()
