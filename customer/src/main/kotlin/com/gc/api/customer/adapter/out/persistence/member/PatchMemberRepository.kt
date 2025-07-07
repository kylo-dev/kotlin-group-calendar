package com.gc.api.customer.adapter.out.persistence.member

import com.gc.api.customer.application.port.out.persistence.member.PatchMemberPort
import com.gc.api.customer.application.service.dto.member.ChangeMemberDto
import com.gc.storage.document.member.MemberDocument
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class PatchMemberRepository(
    val mongoTemplate: MongoTemplate,
): PatchMemberPort {

    override fun changeProfile(request: ChangeMemberDto) {
        val query = Query(
            Criteria.where("_id").`is`(request.memberId)
        )

        val update = Update()
        request.nickname?.let { update.set("nickname", it) }
        request.profile?.let { update.set("profile", it) }

        if (update.updateObject.keys.isNotEmpty()) {
            update.set("updatedAt", LocalDateTime.now())
            mongoTemplate.updateFirst(query, update, MemberDocument::class.java)
        }
    }
}