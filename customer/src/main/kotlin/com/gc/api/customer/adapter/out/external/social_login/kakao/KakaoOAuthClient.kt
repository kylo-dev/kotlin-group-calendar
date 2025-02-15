package com.gc.api.customer.adapter.out.external.social_login.kakao

import com.gc.api.customer.adapter.out.external.dto.response.social_login.kakao.KakaoOAuthToken
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
  name = "kakaoOAuthClient",
  url = "https://kauth.kakao.com"
)
interface KakaoOAuthClient {

  @GetMapping("/oauth/token?grant_type=authorization_code")
  fun getAccessToken(
    @RequestParam("client_id") client: String,
    @RequestParam("redirect_uri") redirect: String,
    @RequestParam("code") code: String
  ): KakaoOAuthToken

}