package com.gc.storage.document.member

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.graphql.data.GraphQlRepository

@GraphQlRepository
interface MemberMongoRepository : MongoRepository<MemberDocument, String> {
}