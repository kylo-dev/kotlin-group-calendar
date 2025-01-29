package com.gc.framework.security

import com.gc.adapter.out.infra.persistence.member.MemberDocument
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority

class CustomAuthentication(
  val memberDocument: MemberDocument
) :Authentication{
  override fun getName(): String {
    return memberDocument.userName
  }

  override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
    val authorities = ArrayList<CustomAuthority>()
    if (memberDocument == null) {
      return authorities
    }
    authorities.add(CustomAuthority.MEMBER)
    return authorities
  }

  override fun getCredentials(): Any {
    throw UnsupportedOperationException()
  }

  override fun getDetails(): Any {
    throw UnsupportedOperationException()
  }

  override fun getPrincipal(): MemberDocument {
    return memberDocument
  }

  override fun isAuthenticated(): Boolean {
    return principal != null
  }

  override fun setAuthenticated(isAuthenticated: Boolean) {
    throw UnsupportedOperationException()
  }
}