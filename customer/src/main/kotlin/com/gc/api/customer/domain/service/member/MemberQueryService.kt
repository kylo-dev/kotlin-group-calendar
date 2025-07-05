package com.gc.api.customer.domain.service.member

import com.gc.api.customer.application.port.out.persistence.member.GetMemberPort
import com.gc.api.customer.domain.model.member.Member
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberQueryService(
  private val getMemberPort: GetMemberPort,
) {

  fun getMemberByEmail(email: String, oauthProvider: String) : Member? {
    return getMemberPort.getMemberByEmailAndOauth(email, oauthProvider)
  }

}