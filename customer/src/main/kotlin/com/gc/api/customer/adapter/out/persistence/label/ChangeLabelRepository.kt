package com.gc.api.customer.adapter.out.persistence.label

import com.gc.api.customer.application.port.out.persistence.label.ChangeMemberLabelPort
import com.gc.api.customer.application.service.dto.label.ChangeMemberLabelDto
import com.gc.storage.document.label.MemberLabelDocument
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.data.mongodb.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class ChangeLabelRepository(
    val operations: MongoOperations,
): ChangeMemberLabelPort, QuerydslRepositorySupport(operations) {

    override fun changeMemberLabel(request: ChangeMemberLabelDto) {

        val query = Query(
            Criteria.where("memberId").`is`(request.memberId)
                .and("labelId").`is`(request.labelId)
        )
        val update =Update()
        request.labelName?.let { update.set("label", it) }
        request.labelColor?.let { update.set("color", it) }

        operations.upsert(query, update, MemberLabelDocument::class.java)

    }
}