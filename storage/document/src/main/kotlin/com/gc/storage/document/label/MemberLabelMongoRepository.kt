package com.gc.storage.document.label

import org.springframework.data.mongodb.repository.MongoRepository

interface MemberLabelMongoRepository: MongoRepository<MemberLabelDocument, String> {

  fun findAllByMemberId(memberId: String): List<MemberLabelDocument>
}