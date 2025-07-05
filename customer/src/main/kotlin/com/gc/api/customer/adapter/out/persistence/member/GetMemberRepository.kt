package com.gc.api.customer.adapter.out.persistence.member

import com.gc.api.customer.application.port.out.persistence.member.GetMemberPort
import com.gc.api.customer.domain.model.member.Member
import com.gc.storage.document.member.MemberDocument
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository


@Repository
class GetMemberRepository(
    private val mongoTemplate: MongoTemplate,
) : GetMemberPort {

    fun getMemberByUserName(): List<MemberDocument> {
        val query = Query(
            Criteria.where("nickname").`is`("kylo")
        )
        return mongoTemplate.find(query, MemberDocument::class.java)
    }

    override fun getMemberByEmailAndOauth(email: String, oauthProvider: String): Member? {
        val query = Query().apply {
            addCriteria(
                Criteria.where("email").`is`(email)
                    .and("oauthProvider").`is`(oauthProvider)
            )
            limit(1)
        }
        val result = mongoTemplate.findOne(query, MemberDocument::class.java)
        return result?.let { Member.from(it) }
    }

}
