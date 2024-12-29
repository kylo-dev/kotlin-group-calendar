package com.gc.common.adapter.out.persistence.jpa

import com.gc.common.adapter.out.persistence.document.MemberDocument
import org.springframework.data.mongodb.repository.MongoRepository

interface MemberJpaRepository : MongoRepository<MemberDocument, String> {
}