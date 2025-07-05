package com.gc.api.customer.framework.security

import com.gc.api.customer.domain.service.member.MemberQueryService
import com.gc.common.logging.logger
import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.time.Duration
import java.util.*
import javax.crypto.SecretKey

@Service
class JwtProvider(
    @Value("\${jwt.secret}") secretKey: String,
    private val memberQueryService: MemberQueryService,
) {

    private val secretKey: SecretKey = Keys.hmacShaKeyFor(secretKey.toByteArray())

    companion object {
        private val ACCESS_EXPIRATION_TIME: Duration = Duration.ofDays(7)
        private val REFRESH_EXPIRATION_TIME: Duration = Duration.ofDays(7)
    }

    fun generateAccessToken(email: String, role: String, oauthProvider: String): String {
        return generateToken(email, role, ACCESS_EXPIRATION_TIME.toMillis(), oauthProvider)
    }

    fun generateRefreshToken(email: String, role: String, oauthProvider: String): String {
        return generateToken(email, role, REFRESH_EXPIRATION_TIME.toMillis(), oauthProvider)
    }

    // Create AT & RT
    private fun generateToken(
        email: String,
        role: String,
        expirationTime: Long,
        oauthProvider: String
    ): String {
        val now = Date()
        val expiration = Date(now.time + expirationTime)

        return Jwts.builder()
            .claims(
                mapOf(
                    "role" to role,
                    "email" to email,
                    "provider" to oauthProvider
                )
            )
            .issuedAt(now)
            .expiration(expiration)
            .signWith(secretKey)
            .compact()
    }

    fun isValidToken(token: String): Boolean {
        return try {
            parseClaims(token)
            true
        } catch (e: Exception) {
            when (e) {
                is SecurityException, is MalformedJwtException -> {
                    logger.info { "Invalid JWT $e" }
                }

                is ExpiredJwtException -> {
                    logger.info { "Expired JWT $e" }
                }

                is UnsupportedJwtException -> {
                    logger.info { "Unsupported JWT $e" }
                }

                is IllegalArgumentException -> {
                    logger.info { "JWT claims string is empty $e" }
                }
            }
            false
        }
    }

    fun parseClaims(token: String): Claims {
        return Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .payload
    }

    fun getAuthentication(accessToken: String): Authentication {
        val claims = parseClaims(accessToken)
        val email = claims["email"].toString()
        val provider = claims["provider"].toString()

        val member = memberQueryService.getMemberByEmail(email, provider)
        return CustomAuthentication(member!!)
    }
}