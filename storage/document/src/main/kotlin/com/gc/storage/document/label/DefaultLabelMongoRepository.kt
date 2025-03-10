package com.gc.storage.document.label

import org.springframework.data.mongodb.repository.MongoRepository

interface DefaultLabelMongoRepository: MongoRepository<DefaultLabelDocument, String> {
}