package com.gc.api.customer.framework.security

import com.gc.api.customer.domain.model.member.Member
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority

class CustomAuthentication(
  val member: Member
) :Authentication{
  override fun getName(): String {
    return member.nickname
  }

  override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
    val authorities = ArrayList<CustomAuthority>()
    if (member == null) {
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

  override fun getPrincipal(): Member {
    return member
  }

  override fun isAuthenticated(): Boolean {
    return principal != null
  }

  override fun setAuthenticated(isAuthenticated: Boolean) {
    throw UnsupportedOperationException()
  }
}