package com.gc.api.customer.adapter.out.persistence.util

import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import java.time.LocalDateTime

inline fun <reified T: Any> MongoTemplate.executeIfUpdateNotEmpty(query: Query, update: Update) {
    if (update.updateObject.keys.isNotEmpty()) {
        update.set("updatedAt", LocalDateTime.now())
        this.updateFirst(query, update, T::class.java)
    }
}