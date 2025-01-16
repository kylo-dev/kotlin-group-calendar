package api.customer.adapter.out.repository

import common.adapter.out.persistence.document.MemberDocument
import common.adapter.out.persistence.document.QMemberDocument
import common.adapter.out.persistence.document.QMemberDocument.memberDocument
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
