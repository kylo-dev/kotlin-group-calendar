package com.gc.api.customer.framework.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.util.AntPathMatcher
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    val jwtProvider: JwtProvider,
) : OncePerRequestFilter() {

    private val pathMatcher = AntPathMatcher()

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        logger.debug("[DO FILTER] JWT Filter")
        if (isPublicUrl(request)) {
            filterChain.doFilter(request, response)
            return
        }

        val token: TokenDto? = resolveToken(request)
        if (token != null && jwtProvider.isValidToken(token.accessToken)) {
            val authentication = jwtProvider.getAuthentication(token.accessToken)
            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain.doFilter(request, response)
        logger.debug("[RES FILTER] JWT Filter")
    }

    private fun resolveToken(request: HttpServletRequest): TokenDto? {
        val bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION)

        return bearerToken?.let {
            val accessToken = bearerToken.substring(JwtConstant.BEARER.length)
            val refreshToken = request.getHeader(JwtConstant.REFESH_TOKEN)
            TokenDto(accessToken, refreshToken)
        }
    }

    private val WHITE_URL_LIST = arrayOf(
        "/api/login/**", "/v3/api-docs/**", "/swagger-resources/**",
        "/swagger-ui/**", "/graphiql/**"
    )

    private fun isPublicUrl(request: HttpServletRequest): Boolean {
        val requestURL = request.requestURI
        return WHITE_URL_LIST.any { pattern -> pathMatcher.match(pattern, requestURL) }
    }

}