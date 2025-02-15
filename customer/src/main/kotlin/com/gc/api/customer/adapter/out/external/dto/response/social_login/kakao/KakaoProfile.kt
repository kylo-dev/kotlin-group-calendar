package com.gc.api.customer.adapter.out.external.dto.response.social_login.kakao

import com.gc.api.customer.adapter.out.external.dto.response.social_login.OAuthProfile

data class KakaoProfile(
  val id: Long,
  val connected_at: String,
  val kakao_account: KakaoAccount,
) : OAuthProfile

data class KakaoAccount(
  val email: String,
  val is_email_verified: Boolean,
  val has_email: Boolean,
  val profile_nickname_needs_agreement: Boolean,
  val email_needs_agreement: Boolean,
  val is_email_valid: Boolean,
  val profile: Profile
)

data class Profile(
  val nickname: String,
  val is_default_nickname: Boolean,
  val thumbnail_image_url: String,
  val profile_image_url: String,
)