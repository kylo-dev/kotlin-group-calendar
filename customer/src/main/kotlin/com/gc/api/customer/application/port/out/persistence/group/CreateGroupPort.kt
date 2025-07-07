package com.gc.api.customer.application.port.out.persistence.group

import com.gc.api.customer.application.service.dto.group.CreateGroupDto
import com.gc.api.customer.domain.model.group.Group

interface CreateGroupPort {
    fun createGroup(createGroupDto: CreateGroupDto): Group
}