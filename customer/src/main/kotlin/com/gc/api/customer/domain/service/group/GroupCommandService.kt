package com.gc.api.customer.domain.service.group

import com.gc.api.customer.application.port.out.persistence.group.CreateGroupPort
import com.gc.api.customer.application.service.dto.group.CreateGroupDto
import com.gc.api.customer.domain.model.group.Group
import org.springframework.stereotype.Service

@Service
class GroupCommandService(
    private val createGroupPort: CreateGroupPort,
) {

    fun createGroup(createGroupDto: CreateGroupDto): Group {
        return createGroupPort.createGroup(createGroupDto)
    }
}