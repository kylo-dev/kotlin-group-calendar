package common.adapter.out.persistence.nosql

import common.adapter.out.persistence.document.MemberDocument
import org.springframework.data.mongodb.repository.MongoRepository

interface MemberMongoRepository : MongoRepository<MemberDocument, String> {
}