package com.gc.api.customer.adapter.out.persistence.group.member

import com.gc.api.customer.adapter.out.persistence.util.updateIfUpdateNotEmpty
import com.gc.api.customer.application.port.out.persistence.group.member.UpdateGroupMemberPort
import com.gc.api.customer.application.service.dto.group.member.UpdateGroupMemberDto
import com.gc.storage.document.group.GroupMemberDocument
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Repository

@Repository
class UpdateGroupMemberRepository(
    private val mongoTemplate: MongoTemplate
): UpdateGroupMemberPort {

    override fun updateGroupMember(request: UpdateGroupMemberDto) {

        val query = Query().apply {
            Criteria.where("groupId").`is`(request.groupId)
                .and("memberId").`is`(request.memberId)
        }

        val update = Update().apply {
            request.memberNickname?.let { set("nickname", it) }
            request.memberProfile?.let { set("profile", it) }
        }

        mongoTemplate.updateIfUpdateNotEmpty<GroupMemberDocument>(query, update)
    }
}