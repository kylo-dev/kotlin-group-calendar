package com.gc.api.customer.adapter.out.external.social_login.kakao

import com.gc.api.customer.adapter.out.external.social_login.dto.kakao.KakaoProfile
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(
  name = "kakaoProfileClient",
  url = "https://kapi.kakao.com"
)
interface KakaoProfileClient {

  @GetMapping("/v2/user/me")
  fun getProfile(
    @RequestHeader("Authorization") bearerToken: String
  ): KakaoProfile
}