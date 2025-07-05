package com.gc.storage.document.member

import com.gc.storage.document.common.BaseTimeEntity
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(value = "members")
data class MemberDocument(
  val nickname: String,
  val email: String,
  val isActive: Boolean,
  val isAdmin: Boolean,
  val profile: String,
  val oauthProvider: String,
  val isNotificationEnabled: Boolean,
  val isMondayStart: Boolean,
  @Id
  val id: String? = null,
): BaseTimeEntity() {


}