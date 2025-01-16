package common.adapter.out.persistence.document

import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(value = "members")
@Entity
data class MemberDocument(
  val userName: String,
  val email: String,
  val isActive: Boolean,
  val isAdmin: Boolean,
  val profile: String,
  val oauthProvider: String,
  @Id
  val id: String? = null,
  override val createdAt: LocalDateTime = LocalDateTime.now(),
  override val updatedAt: LocalDateTime = LocalDateTime.now(),
): BaseTimeEntity() {

}