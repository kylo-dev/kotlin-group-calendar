package com.gc.api.customer.application.port.out.external.social_login

import com.gc.api.customer.adapter.out.external.dto.response.social_login.OAuthProfile

interface SocialLoginPort {
  fun getProfile(accessCode: String): OAuthProfile
}