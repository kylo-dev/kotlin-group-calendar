package com.gc.api.customer.application.port.out.persistence.member

import com.gc.api.customer.application.service.dto.member.ChangeMemberDto

interface PatchMemberPort {
    fun changeProfile(request: ChangeMemberDto)
}