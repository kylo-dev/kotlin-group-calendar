package com.gc.api.customer.domain.service.group

import com.gc.api.customer.application.port.out.persistence.group.GetGroupPort
import com.gc.api.customer.domain.model.group.Group
import org.springframework.stereotype.Service

@Service
class GroupQueryService(
    private val getGroupPort: GetGroupPort,
) {

    fun findGroupsByMemberId(memberId: String): List<Group> {
        return getGroupPort.getGroupsByMemberId(memberId)
    }
}