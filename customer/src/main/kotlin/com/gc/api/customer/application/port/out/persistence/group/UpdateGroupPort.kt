package com.gc.api.customer.application.port.out.persistence.group

import com.gc.api.customer.application.service.dto.group.UpdateGroupDto

interface UpdateGroupPort {
    fun updateGroup(request: UpdateGroupDto)
}