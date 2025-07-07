package com.gc.api.customer.domain.service.group.member

import com.gc.api.customer.application.port.out.persistence.group.member.CreateGroupMemberPort
import com.gc.api.customer.application.port.out.persistence.group.member.UpdateGroupMemberPort
import com.gc.api.customer.application.service.dto.group.CreateGroupDto
import com.gc.api.customer.application.service.dto.group.member.JoinGroupMemberDto
import com.gc.api.customer.application.service.dto.group.member.UpdateGroupMemberDto
import com.gc.api.customer.domain.model.group.Group
import org.springframework.stereotype.Service

@Service
class GroupMemberCommandService(
    private val createGroupMemberPort: CreateGroupMemberPort,
    private val updateGroupMemberPort: UpdateGroupMemberPort,
) {

    fun registerGroupMember(createGroupDto: CreateGroupDto, group: Group) {
        createGroupMemberPort.registerGroupMember(createGroupDto, group)
    }

    fun joinGroup(joinGroupMemberDto: JoinGroupMemberDto) {
        createGroupMemberPort.joinGroup(joinGroupMemberDto)
    }

    fun updateGroupMember(updateGroupMemberDto: UpdateGroupMemberDto) {
        updateGroupMemberPort.updateGroupMember(updateGroupMemberDto)
    }


}