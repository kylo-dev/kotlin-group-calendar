package com.gc.api.customer.adapter.out.external.social_login.dto.naver

import com.fasterxml.jackson.annotation.JsonProperty
import com.gc.api.customer.adapter.out.external.social_login.dto.OAuthToken

data class NaverOAuthToken(
  @JsonProperty("access_token") override val accessToken: String,
  @JsonProperty("refresh_token") val refreshToken: String,
  @JsonProperty("token_type") val tokenType: String,
  @JsonProperty("expires_in") val expiresIn: Int,
  @JsonProperty("error") val error: String?,
  @JsonProperty("error_description") val errorDescription: String?
) : OAuthToken
