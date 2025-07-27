package com.gc.api.customer.adapter.out.persistence.group_event

import com.gc.api.customer.application.port.out.persistence.group_event.GetGroupEventPort
import com.gc.api.customer.domain.model.event.EventPeriod
import com.gc.api.customer.domain.model.group.GroupEvent
import com.gc.storage.document.group_event.GroupEventDocument
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository

@Repository
class GetGroupEventRepository(
    private val mongoTemplate: MongoTemplate,
): GetGroupEventPort {

    override fun findTop3EventsByGroup(groupIds: List<String>): List<GroupEvent> {
        val results = groupIds.map {
            mongoTemplate.find(
                Query(Criteria.where("groupId").`is`(it))
                    .with(Sort.by(Sort.Direction.DESC, "startDateTime"))
                    .limit(3),
                GroupEventDocument::class.java
            )
        }.flatten()
        return results.map { documentToModel(it) }
    }

    private fun documentToModel(document: GroupEventDocument): GroupEvent {
        return GroupEvent(
            groupId = document.groupId,
            groupEventId = document.id!!,
            eventTitle = document.title,
            eventPeriod = EventPeriod(document.startDateTime, document.endDateTime),
        )
    }
}