package com.gc.api.customer.adapter.out.persistence.label

import com.gc.api.customer.application.port.out.persistence.label.ChangeMemberLabelPort
import com.gc.api.customer.application.service.dto.label.ChangeMemberLabelDto
import com.gc.storage.document.label.MemberLabelDocument
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Repository

@Repository
class ChangeLabelRepository(
    private val mongoTemplate: MongoTemplate,
): ChangeMemberLabelPort {

    override fun changeMemberLabel(request: ChangeMemberLabelDto) {

        val query = Query(
            Criteria.where("memberId").`is`(request.memberId)
                .and("labelId").`is`(request.labelId)
        )
        val update =Update()
        request.labelName?.let { update.set("label", it) }
        request.labelColor?.let { update.set("color", it) }

        mongoTemplate.upsert(query, update, MemberLabelDocument::class.java)

    }
}