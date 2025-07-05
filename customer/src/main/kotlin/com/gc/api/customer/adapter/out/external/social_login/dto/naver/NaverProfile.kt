package com.gc.api.customer.adapter.out.external.social_login.dto.naver

import com.fasterxml.jackson.annotation.JsonProperty
import com.gc.api.customer.adapter.out.external.social_login.dto.OAuthProfile

data class NaverProfile(
    val response: NaverProfileResponse,
) : OAuthProfile

data class NaverProfileResponse(
  @JsonProperty("id") val id: String,
  @JsonProperty("email")val email: String,
  @JsonProperty("nickname") val nickname: String,
  @JsonProperty("profile_image") val profileImage: String,
)
