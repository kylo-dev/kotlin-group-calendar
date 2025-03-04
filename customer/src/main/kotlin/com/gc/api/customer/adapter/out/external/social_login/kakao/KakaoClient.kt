package com.gc.api.customer.adapter.out.external.social_login.kakao

import com.gc.api.customer.adapter.out.external.dto.response.social_login.OAuthProfile
import com.gc.api.customer.adapter.out.external.dto.response.social_login.kakao.KakaoOAuthToken
import com.gc.api.customer.application.port.out.external.social_login.SocialLoginPort
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class KakaoClient(
  @Value("\${kakao.auth.client}") private val client: String,
  @Value("\${kakao.auth.redirect}") private val redirect: String,
  private val kakaoOauthClient: KakaoOAuthClient,
  private val kakaoProfileClient: KakaoProfileClient,
): SocialLoginPort {

  override fun getProfile(accessCode: String): OAuthProfile {
    val accessToken: KakaoOAuthToken = kakaoOauthClient.getAccessToken(client, redirect, accessCode)
    return kakaoProfileClient.getProfile("Bearer ${accessToken.accessToken}")
  }
}