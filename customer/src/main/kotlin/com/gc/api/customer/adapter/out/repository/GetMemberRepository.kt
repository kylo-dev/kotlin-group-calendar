package com.gc.api.customer.adapter.out.repository

import com.gc.adapter.out.infra.persistence.member.MemberDocument
import com.gc.adapter.out.infra.persistence.member.QMemberDocument
import com.gc.adapter.out.infra.persistence.member.QMemberDocument.memberDocument
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class GetMemberRepository(
  val operations: MongoOperations,
): QuerydslRepositorySupport(operations) {

  fun getMemberByUserName(): List<MemberDocument> {

    return from(QMemberDocument.memberDocument)
      .where(memberDocument.userName.eq("kylo"))
      .fetch()

  }
}
