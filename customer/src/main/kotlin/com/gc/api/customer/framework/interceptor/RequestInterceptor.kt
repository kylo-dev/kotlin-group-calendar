package com.gc.api.customer.framework.interceptor

import com.gc.api.customer.domain.model.member.Member
import com.gc.api.customer.framework.annotation.RequestInfo
import com.gc.api.customer.framework.annotation.RequireAuth
import com.gc.api.customer.framework.security.CustomAuthentication
import com.gc.common.exception.CustomAuthenticationException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.jvm.kotlinFunction

@Component
class RequestInterceptor(
    private val requestInfo: RequestInfo
) : HandlerInterceptor {

    override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any
    ): Boolean {
        val member = getAuthentication()

        (handler as? HandlerMethod)?.let { handlerMethod ->
            val kFunction = handlerMethod.method.kotlinFunction
            if (kFunction?.hasAnnotation<RequireAuth>() == true && member == null) {
                throw CustomAuthenticationException("로그인이 필요합니다")
            }
        }

        member?.let { requestInfo.setRequestMember(it) }
        return true
    }

    fun getAuthentication(): Member? =
        (SecurityContextHolder.getContext().authentication
                as? CustomAuthentication)?.member
}
