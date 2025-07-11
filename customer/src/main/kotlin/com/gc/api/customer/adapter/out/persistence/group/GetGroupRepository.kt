package com.gc.api.customer.adapter.out.persistence.group

import com.gc.api.customer.application.port.out.persistence.group.GetGroupPort
import com.gc.api.customer.domain.model.group.Group
import com.gc.storage.document.group.GroupDocument
import com.gc.storage.document.group.GroupMemberMongoRepository
import com.gc.storage.document.group.GroupMongoRepository
import org.springframework.stereotype.Repository

@Repository
class GetGroupRepository(
    private val groupMemberMongoRepository: GroupMemberMongoRepository,
    private val groupMongoRepository: GroupMongoRepository,
) : GetGroupPort {

    override fun getGroupsByMemberId(memberId: String): List<Group> {

        // 1. 속한 그룹 조회
        val groupMembers = groupMemberMongoRepository.findAllByMemberId(memberId)
        val groupIds = groupMembers.map {
            it.groupId
        }

        // 2. 모든 그룹 정보 조회
        val groups = groupMongoRepository.findAllById(groupIds)
        return groups.map { documentToModel(it) }
    }

    private fun documentToModel(document: GroupDocument): Group {
        return Group(
            id = document.id!!,
            name = document.name,
            profile = document.profile,
            description = document.description,
            link = document.link,
        )
    }
}