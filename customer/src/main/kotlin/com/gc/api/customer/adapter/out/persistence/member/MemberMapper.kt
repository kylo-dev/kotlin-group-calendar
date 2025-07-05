package com.gc.api.customer.adapter.out.persistence.member

import com.gc.api.customer.adapter.out.external.social_login.dto.OAuthProfile
import com.gc.api.customer.adapter.out.external.social_login.dto.kakao.KakaoProfile
import com.gc.api.customer.adapter.out.external.social_login.dto.naver.NaverProfile
import com.gc.api.customer.domain.model.OauthProvider
import com.gc.common.exception.CustomBadRequestException
import com.gc.storage.document.member.MemberDocument

class MemberMapper {

    companion object {
        fun from(oAuthProfile: OAuthProfile): MemberDocument {
            return when (oAuthProfile) {
                is KakaoProfile -> {
                    val kakaoProfile = oAuthProfile.kakao_account
                    MemberDocument(
                        nickname = kakaoProfile.profile.nickname,
                        email = kakaoProfile.email,
                        isActive = true,
                        isAdmin = false,
                        profile = kakaoProfile.profile.profile_image_url,
                        oauthProvider = OauthProvider.KAKAO.name,
                        isNotificationEnabled = false,
                        isMondayStart = true
                    )
                }

                is NaverProfile -> {
                    val naverProfile = oAuthProfile.response
                    MemberDocument(
                        nickname = naverProfile.nickname,
                        email = naverProfile.email,
                        isActive = true,
                        isAdmin = false,
                        profile = naverProfile.profileImage,
                        oauthProvider = OauthProvider.NAVER.name,
                        isNotificationEnabled = false,
                        isMondayStart = true
                    )
                }

                else -> throw CustomBadRequestException("잘못된 사용자 프로필 정보입니다.")
            }
        }
    }
}