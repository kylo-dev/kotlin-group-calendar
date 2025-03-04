package com.gc.api.customer.application.service.facade

import com.gc.api.customer.adapter.out.external.dto.response.social_login.OAuthProfile
import com.gc.api.customer.adapter.out.external.dto.response.social_login.kakao.KakaoProfile
import com.gc.api.customer.adapter.out.external.dto.response.social_login.naver.NaverProfile
import com.gc.api.customer.domain.model.OauthProvider
import com.gc.api.customer.domain.model.member.Member
import com.gc.api.customer.domain.service.member.MemberCommandService
import com.gc.api.customer.domain.service.member.MemberQueryService
import com.gc.api.customer.framework.security.CustomAuthority
import com.gc.api.customer.framework.security.JwtProvider
import com.gc.api.customer.framework.security.TokenDto
import com.gc.common.framework.exception.CustomBadRequestException
import org.springframework.stereotype.Service

@Service
class MemberFacade(
  private val memberQueryService: MemberQueryService,
  private val memberCommandService: MemberCommandService,
  private val jwtProvider: JwtProvider,
) {

  fun loginOrJoin(oAuthProfile: OAuthProfile): TokenDto {

    val provider = when(oAuthProfile) {
      is KakaoProfile -> OauthProvider.KAKAO to oAuthProfile.kakao_account.email
      is NaverProfile -> OauthProvider.NAVER to oAuthProfile.response.email
      else -> throw CustomBadRequestException("지원하지 않는 소셜 로그인입니다.")
    }

    val (oauthProvider, email) = provider

    val member = memberQueryService.getMemberByEmail(email, oauthProvider.name) ?:
    memberCommandService.singUp(oAuthProfile)
    return generateToken(member)

  }

  private fun generateToken(member: Member): TokenDto {
    val authority = CustomAuthority.getAuthority(member.isAdmin)
    return TokenDto(
      jwtProvider.generateAccessToken(member.email, authority, member.oauthProvider.name),
      jwtProvider.generateRefreshToken(member.email, authority, member.oauthProvider.name)
    )
  }
}