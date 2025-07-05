package com.gc.api.customer.framework.annotation

import com.gc.api.customer.domain.model.member.Member
import org.springframework.stereotype.Component
import org.springframework.web.context.annotation.RequestScope

@Component
@RequestScope
class RequestInfo {
    lateinit var member: Member

    fun setRequestMember(member: Member) {
        this.member = member
    }
}