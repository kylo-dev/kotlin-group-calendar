package com.gc.api.customer.adapter.out.external.social_login.naver

import com.gc.api.customer.adapter.out.external.dto.response.social_login.OAuthProfile
import com.gc.api.customer.adapter.out.external.dto.response.social_login.naver.NaverOAuthToken
import com.gc.api.customer.application.port.out.external.social_login.SocialLoginPort
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class NaverClient(
  @Value("\${naver.auth.client_id}") private val clientId: String,
  @Value("\${naver.auth.client_secret}") private val clientSecret: String,
  private val naverOAuthClient: NaverOAuthClient,
  private val naverProfileClient: NaverProfileClient,
): SocialLoginPort {

  override fun getProfile(accessCode: String): OAuthProfile {
    val accessToken: NaverOAuthToken = naverOAuthClient.getAccessToken(clientId, clientSecret, accessCode)
    return naverProfileClient.getProfile("Bearer ${accessToken.accessToken}")
  }
}