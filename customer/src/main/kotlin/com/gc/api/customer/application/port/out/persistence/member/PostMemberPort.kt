package com.gc.api.customer.application.port.out.persistence.member

import com.gc.api.customer.adapter.out.external.dto.response.social_login.OAuthProfile
import com.gc.api.customer.domain.model.member.Member

interface PostMemberPort {

  fun save(oauthProfile: OAuthProfile): Member
}