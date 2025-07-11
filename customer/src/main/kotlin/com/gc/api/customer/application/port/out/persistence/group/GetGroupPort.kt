package com.gc.api.customer.application.port.out.persistence.group

import com.gc.api.customer.domain.model.group.Group

interface GetGroupPort {
    fun getGroupsByMemberId(memberId: String): List<Group>
}