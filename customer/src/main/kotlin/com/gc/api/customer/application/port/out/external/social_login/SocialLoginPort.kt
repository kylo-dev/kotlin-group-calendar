package com.gc.api.customer.application.port.out.external.social_login

import com.gc.api.customer.adapter.out.external.dto.response.social_login.OAuthProfile
import com.gc.api.customer.adapter.out.external.dto.response.social_login.OAuthToken

interface SocialLoginPort {
  fun getAccessToken(accessCode: String): OAuthToken
  fun getProfile(oAuthToken: OAuthToken): OAuthProfile
}