package com.gc.api.customer.adapter.out.external.social_login.naver

import com.gc.api.customer.adapter.out.external.social_login.dto.naver.NaverProfile
import com.gc.api.customer.framework.config.feign.FeignClientConfig
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(
  name = "naverProfileClient",
  url = "https://openapi.naver.com",
  configuration = [FeignClientConfig::class]
)
interface NaverProfileClient {

  @GetMapping("/v1/nid/me")
  fun getProfile(
    @RequestHeader("Authorization") bearerToken: String
  ): NaverProfile
}