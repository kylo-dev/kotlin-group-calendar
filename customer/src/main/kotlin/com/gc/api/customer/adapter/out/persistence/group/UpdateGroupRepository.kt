package com.gc.api.customer.adapter.out.persistence.group

import com.gc.api.customer.adapter.out.persistence.util.executeIfUpdateNotEmpty
import com.gc.api.customer.application.port.out.persistence.group.UpdateGroupPort
import com.gc.api.customer.application.service.dto.group.UpdateGroupDto
import com.gc.storage.document.group.GroupDocument
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Repository

@Repository
class UpdateGroupRepository(
    private val mongoTemplate: MongoTemplate,
): UpdateGroupPort {

    override fun updateGroup(request: UpdateGroupDto) {
        val query = Query().apply {
            Criteria.where("_id").`is`(request.groupId)
        }

        val update = Update().apply {
            request.groupName?.let { set("name", it) }
            request.groupDescription?.let { set("description", it) }
        }

        mongoTemplate.executeIfUpdateNotEmpty<GroupDocument>(query, update)
    }
}