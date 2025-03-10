package com.gc.api.customer.framework.interceptor

import com.gc.api.customer.domain.model.member.Member
import com.gc.api.customer.framework.annotation.RequestInfo
import com.gc.api.customer.framework.annotation.RequireAuth
import com.gc.api.customer.framework.security.CustomAuthentication
import com.gc.common.framework.exception.CustomAuthenticationException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor

@Component
class RequestInterceptor(
  private val requestInfo: RequestInfo
): HandlerInterceptor {

  override fun preHandle(
    request: HttpServletRequest,
    response: HttpServletResponse,
    handler: Any
  ): Boolean {
    val member = getAuthentication()

    if (handler is HandlerMethod) {
      if (handler.hasMethodAnnotation(RequireAuth::class.java) && member == null) {
        throw CustomAuthenticationException("로그인이 필요합니다")
      }
    }
    member?.let { requestInfo.setRequestMember(member) }

    return true
  }

  fun getAuthentication(): Member? {
    val authentication = SecurityContextHolder.getContext().authentication as CustomAuthentication
    return authentication.member
  }
}