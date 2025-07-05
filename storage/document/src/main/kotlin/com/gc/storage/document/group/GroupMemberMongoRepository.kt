package com.gc.storage.document.group

import org.springframework.data.mongodb.repository.MongoRepository

interface GroupMemberMongoRepository: MongoRepository<GroupMemberDocument, String> {
}