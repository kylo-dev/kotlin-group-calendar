package com.gc.storage.document.member

import org.springframework.data.mongodb.repository.MongoRepository

interface MemberMongoRepository : MongoRepository<MemberDocument, String> {
}