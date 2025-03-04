package com.gc.api.customer.domain.model.member

import com.gc.api.customer.domain.model.OauthProvider
import com.gc.storage.document.member.MemberDocument

data class Member(
  val id: String,
  val nickname: String,
  val email: String,
  val isNotificationEnabled: Boolean,
  val profile: String,
  val oauthProvider: OauthProvider,
  val isAdmin: Boolean,
  val isMondayStart: Boolean,
) {

  companion object {
    fun from(memberDocument: MemberDocument): Member {
      return Member(
        id = memberDocument.id!!,
        nickname = memberDocument.nickname,
        email = memberDocument.email,
        isNotificationEnabled = memberDocument.isNotificationEnabled,
        profile = memberDocument.profile,
        oauthProvider = OauthProvider.valueOf(memberDocument.oauthProvider),
        isAdmin = memberDocument.isAdmin,
        isMondayStart = memberDocument.isMondayStart,
      )
    }
  }
}