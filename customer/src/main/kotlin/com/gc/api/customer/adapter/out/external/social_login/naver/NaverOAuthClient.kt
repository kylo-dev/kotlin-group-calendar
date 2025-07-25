package com.gc.api.customer.adapter.out.external.social_login.naver

import com.gc.api.customer.adapter.out.external.social_login.dto.naver.NaverOAuthToken
import com.gc.api.customer.framework.config.feign.FeignRequestInterceptor
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
  name = "naverOAuthClient",
  url = "https://nid.naver.com",
  configuration = [FeignRequestInterceptor::class]
)
interface NaverOAuthClient {

  @GetMapping("/oauth2.0/token?grant_type=authorization_code&state=STATE_STRING")
  fun getAccessToken(
    @RequestParam("client_id") clientId: String,
    @RequestParam("client_secret") clientSecret: String,
    @RequestParam("code") code: String
  ): NaverOAuthToken

}