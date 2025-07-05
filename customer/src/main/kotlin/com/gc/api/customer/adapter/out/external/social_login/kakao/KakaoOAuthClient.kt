package com.gc.api.customer.adapter.out.external.social_login.kakao

import com.gc.api.customer.adapter.out.external.social_login.dto.kakao.KakaoOAuthToken
import com.gc.api.customer.framework.config.feign.FeignRequestInterceptor
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
  name = "kakaoOAuthClient",
  url = "https://kauth.kakao.com",
  configuration = [FeignRequestInterceptor::class]
)
interface KakaoOAuthClient {

  @GetMapping("/oauth/token?grant_type=authorization_code")
  fun getAccessToken(
    @RequestParam("client_id") client: String,
    @RequestParam("redirect_uri") redirect: String,
    @RequestParam("code") code: String
  ): KakaoOAuthToken

}