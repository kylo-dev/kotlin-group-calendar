package com.gc.api.customer.application.port.out.persistence.member

import com.gc.api.customer.domain.model.member.Member

interface GetMemberPort {

    fun getMemberByEmailAndOauth(email: String, oauthProvider: String): Member?
}