package com.gc.api.customer.application.port.out.persistence.group.member

import com.gc.api.customer.application.service.dto.group.CreateGroupDto
import com.gc.api.customer.domain.model.group.Group

interface CreateGroupMemberPort {
    fun registerGroupMember(request: CreateGroupDto, group: Group)
}