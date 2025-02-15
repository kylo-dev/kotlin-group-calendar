package com.gc.api.customer.domain.service.auth

import com.gc.api.customer.adapter.out.external.dto.response.social_login.kakao.KakaoProfile
import com.gc.api.customer.adapter.out.external.dto.response.social_login.naver.NaverProfile
import com.gc.api.customer.application.port.out.external.social_login.SocialLoginPort
import org.springframework.stereotype.Service

@Service
class AuthService(
  private val kakaoClient: SocialLoginPort,
  private val naverClient: SocialLoginPort,
) {

  fun kakaoLogin(accessCode: String): KakaoProfile {

    val kakaoOAuthToken = kakaoClient.getAccessToken(accessCode)
    val requestProfile = kakaoClient.getProfile(kakaoOAuthToken)

    return requestProfile as KakaoProfile
  }

  fun naverLogin(accessCode: String): NaverProfile {
    val naverOAuthToken = naverClient.getAccessToken(accessCode)
    val naverProfile = naverClient.getProfile(naverOAuthToken)

    return naverProfile as NaverProfile
  }
}