package com.gc.storage.document.device_token

import com.gc.storage.document.common.BaseTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("device_tokens")
@Entity
data class DeviceTokenDocument(
  val memberId: String,
  val deviceToken: String,
  @Id
  val id: String? = null,
  ): BaseTimeEntity() {

}
