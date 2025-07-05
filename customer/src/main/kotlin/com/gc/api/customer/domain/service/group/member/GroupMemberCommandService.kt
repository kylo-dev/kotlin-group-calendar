package com.gc.api.customer.domain.service.group.member

import com.gc.api.customer.application.port.out.persistence.group.member.CreateGroupMemberPort
import com.gc.api.customer.application.service.dto.group.CreateGroupDto
import com.gc.api.customer.domain.model.group.Group
import org.springframework.stereotype.Service

@Service
class GroupMemberCommandService(
    private val createGroupMemberPort: CreateGroupMemberPort,
) {

    fun registerGroupMember(createGroupDto: CreateGroupDto, group: Group) {
        createGroupMemberPort.registerGroupMember(createGroupDto, group)
    }
}