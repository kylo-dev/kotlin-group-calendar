package com.gc.api.customer.adapter.out.external.dto.response.social_login.kakao

import com.fasterxml.jackson.annotation.JsonProperty
import com.gc.api.customer.adapter.out.external.dto.response.social_login.OAuthToken

data class KakaoOAuthToken(
  @JsonProperty("access_token") override val accessToken: String,
  @JsonProperty("token_type") val tokenType: String,
  @JsonProperty("refresh_token") val refreshToken: String,
  @JsonProperty("expires_in") val expiresIn: Int,
  @JsonProperty("scope") val scope: String,
  @JsonProperty("refresh_token_expires_in") val refreshTokenExpiresIn: Int,
) : OAuthToken
