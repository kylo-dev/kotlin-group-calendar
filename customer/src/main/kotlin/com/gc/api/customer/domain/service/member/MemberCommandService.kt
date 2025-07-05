package com.gc.api.customer.domain.service.member

import com.gc.api.customer.adapter.out.external.social_login.dto.OAuthProfile
import com.gc.api.customer.application.port.out.persistence.member.PostMemberPort
import com.gc.api.customer.domain.model.member.Member
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MemberCommandService(
  val postMemberPort: PostMemberPort,
) {

  fun singUp(oAuthProfile: OAuthProfile): Member {
    return postMemberPort.save(oAuthProfile)
  }
}