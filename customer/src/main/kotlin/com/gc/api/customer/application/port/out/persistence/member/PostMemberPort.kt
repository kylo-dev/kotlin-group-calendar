package com.gc.api.customer.application.port.out.persistence.member

import com.gc.api.customer.adapter.out.external.social_login.dto.OAuthProfile
import com.gc.api.customer.domain.model.member.Member

interface PostMemberPort {

  fun save(oauthProfile: OAuthProfile): Member
}