package com.gc.common.framework.logging

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.util.ContentCachingResponseWrapper

@Component
class RequestCachingFilter : OncePerRequestFilter() {

  override fun doFilterInternal(
    request: HttpServletRequest,
    response: HttpServletResponse,
    filterChain: FilterChain
  ) {
    logger.info("[FILTER] RequestCachingFilter")
    val cachingRequest = CachingBodyHttpServletRequest(request)
    val cachingResponse = ContentCachingResponseWrapper(response)
    filterChain.doFilter(cachingRequest, cachingResponse)

    cachingResponse.copyBodyToResponse()
  }
}