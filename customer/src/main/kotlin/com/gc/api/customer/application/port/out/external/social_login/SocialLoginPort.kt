package com.gc.api.customer.application.port.out.external.social_login

import com.gc.api.customer.adapter.out.external.social_login.dto.OAuthProfile

interface SocialLoginPort {
  fun getProfile(accessCode: String): OAuthProfile
}