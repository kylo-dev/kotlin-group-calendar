package com.gc.storage.document.event

import org.springframework.data.mongodb.repository.MongoRepository

interface EventMongoRepository: MongoRepository<EventDocument, String> {
}