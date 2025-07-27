package com.gc.api.customer.adapter.out.persistence.group.member

import com.gc.api.customer.application.port.out.persistence.group.member.GetGroupMemberPort
import com.gc.api.customer.domain.model.group.GroupMember
import com.gc.storage.document.group.GroupMemberDocument
import com.gc.storage.document.group.GroupMemberMongoRepository
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository

@Repository
class GetGroupMemberRepository(
    private val groupMemberMongoRepository: GroupMemberMongoRepository,
    private val mongoTemplate: MongoTemplate,
): GetGroupMemberPort {

    override fun getGroupMembers(groupId: String): List<GroupMember> {
        val results = groupMemberMongoRepository.findAllByGroupId(groupId)
        return results.map { documentToModel(it) }
    }

    override fun getAllGroupMembers(groupIds: List<String>): List<GroupMember> {

        val query = Query().apply {
            Criteria.where("groupId").`in`(groupIds)
        }
        val results = mongoTemplate.find(query, GroupMemberDocument::class.java)
        return results.map { documentToModel(it) }
    }

    private fun documentToModel(document: GroupMemberDocument): GroupMember {
        return GroupMember(
            groupId = document.groupId,
            memberId = document.memberId,
            memberNickname = document.nickname,
            memberProfile = document.profile,
        )
    }
}