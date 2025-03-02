package com.gc.api.customer.adapter.out.persistence

import com.gc.storage.document.member.MemberDocument
import com.gc.storage.document.member.QMemberDocument
import com.gc.storage.document.member.QMemberDocument.memberDocument
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class GetMemberRepository(
  val operations: MongoOperations,
): QuerydslRepositorySupport(operations) {

  fun getMemberByUserName(): List<MemberDocument> {

    return from(QMemberDocument.memberDocument)
      .where(memberDocument.nickname.eq("kylo"))
      .fetch()

  }
}
