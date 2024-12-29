package com.gc.common.adapter.out.persistence.document

import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(value = "members")
@AllArgsConstructor
@NoArgsConstructor
@Getter
class MemberDocument(
  val userName: String,
  val email: String,
  val isActive: Boolean,
  val isAdmin: Boolean,
  val profile: String,
  val oauthProvider: String,
  @Id
  val id: String? = null,
) {
}