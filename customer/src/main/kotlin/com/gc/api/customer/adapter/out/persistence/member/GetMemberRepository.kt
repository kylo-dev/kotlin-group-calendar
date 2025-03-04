package com.gc.api.customer.adapter.out.persistence.member

import com.gc.api.customer.application.port.out.persistence.member.GetMemberPort
import com.gc.api.customer.domain.model.member.Member
import com.gc.storage.document.member.MemberDocument
import com.gc.storage.document.member.QMemberDocument.memberDocument
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class GetMemberRepository(
  val operations: MongoOperations,
): QuerydslRepositorySupport(operations), GetMemberPort {

  fun getMemberByUserName(): List<MemberDocument> {

    return from(memberDocument)
      .where(memberDocument.nickname.eq("kylo"))
      .fetch()

  }

  override fun getMemberByEmailAndOauth(email: String, oauthProvider: String): Member? {
    val memberDocument = from(memberDocument)
      .where(
        memberDocument.email.eq(email)
          .and(memberDocument.oauthProvider.eq(oauthProvider))
      )
      .fetch()
    return memberDocument.firstOrNull()?.let { Member.from(it) }
  }

  override fun existsMemberByEmailAndOauth(email: String, oauthProvider: String): Boolean {
    return from(memberDocument)
      .where(
        memberDocument.email.eq(email)
          .and(memberDocument.oauthProvider.eq(oauthProvider))
      )
      .fetchCount() > 0
  }
}
