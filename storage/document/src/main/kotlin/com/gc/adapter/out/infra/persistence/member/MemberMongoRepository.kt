package com.gc.adapter.out.infra.persistence.member

import org.springframework.data.mongodb.repository.MongoRepository

interface MemberMongoRepository : MongoRepository<MemberDocument, String> {
}