package com.gc.api.customer.application.service.facade.group

import com.gc.api.customer.application.service.dto.group.CreateGroupDto
import com.gc.api.customer.domain.model.group.Group
import com.gc.api.customer.domain.service.group.GroupCommandService
import com.gc.api.customer.domain.service.group.member.GroupMemberCommandService
import org.springframework.stereotype.Service

@Service
class GroupFacade(
//    private val inviteTokenProvider: InviteTokenProvider,
    private val groupCommandService: GroupCommandService,
    private val groupMemberCommandService: GroupMemberCommandService,
) {

    fun createGroup(createGroupDto: CreateGroupDto): Group {

        // 1. 그룹 생성
        val newGroup = groupCommandService.createGroup(createGroupDto)
        // 2. 그룹 참가
        groupMemberCommandService.joinGroup(createGroupDto.toJoinGroupDto(newGroup))
        return newGroup
    }
}