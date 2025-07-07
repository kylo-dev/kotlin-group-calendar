package com.gc.api.customer.adapter.out.persistence.group

import com.gc.api.customer.application.port.out.persistence.group.CreateGroupPort
import com.gc.api.customer.application.service.dto.group.CreateGroupDto
import com.gc.api.customer.domain.model.group.Group
import com.gc.storage.document.group.GroupDocument
import com.gc.storage.document.group.GroupMongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class PostGroupRepository(
    private val groupRepository: GroupMongoRepository,
): CreateGroupPort {

    override fun createGroup(createGroupDto: CreateGroupDto): Group {

        val groupDocument = groupRepository.save(
            createGroupDocument(createGroupDto)
        )
        return Group.loadFromDocument(groupDocument)
    }

    private fun createGroupDocument(request: CreateGroupDto): GroupDocument {
        return GroupDocument(
            name = request.groupName,
            profile = request.groupProfile,
            description = request.groupDescription,
            link = UUID.randomUUID().toString(),
        )
    }
}