package com.gc.api.customer.adapter.out.external.social_login.kakao

import com.gc.api.customer.adapter.out.external.dto.response.social_login.OAuthToken
import com.gc.api.customer.adapter.out.external.dto.response.social_login.kakao.KakaoOAuthToken
import com.gc.api.customer.adapter.out.external.dto.response.social_login.kakao.KakaoProfile
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

  override fun getAccessToken(accessCode: String): KakaoOAuthToken {
    return kakaoOauthClient.getAccessToken(client, redirect, accessCode)
  }

  override fun getProfile(oAuthToken: OAuthToken): KakaoProfile {
    return kakaoProfileClient.getProfile("Bearer ${oAuthToken.accessToken}")
  }

}