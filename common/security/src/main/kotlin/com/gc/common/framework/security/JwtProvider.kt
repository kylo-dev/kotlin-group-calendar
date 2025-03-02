package com.gc.common.framework.security

import com.gc.utils.logger
import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.Duration
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtProvider(
  @Value("\${jwt.secret}") secretKey: String,
  // TODO: storage:document 구현 후 사용
//  private val memberService: MemberService,
) {

  private val secretKey: SecretKey = Keys.hmacShaKeyFor(secretKey.toByteArray())


  companion object {
    private val ACCESS_EXPIRATION_TIME: Duration = Duration.ofHours(1)
  }

  fun generateAccessToken(email: String, roles: List<String>): String {
    return generateToken(email, roles, ACCESS_EXPIRATION_TIME.toMillis())
  }

  // Create AT & RT
  fun generateToken(email: String, roles: List<String>, expirationTime: Long): String {
    val now = Date()
    val expiration = Date(now.time + expirationTime)

    return Jwts.builder()
      .claims(mapOf("roles" to roles, "email" to email))
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
          logger.info {"Invalid JWT ${e}" }
        }
        is ExpiredJwtException -> {
          logger.info {"Expired JWT ${e}"}
        }
        is UnsupportedJwtException -> {
          logger.info {"Unsupported JWT ${e}"}
        }
        is IllegalArgumentException -> {
          logger.info {"JWT claims string is empty ${e}"}
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

//  fun getAuthentication(accessToken: String): Authentication {
//    val claims = parseClaims(accessToken)
//    val email = claims.get("email").toString()
//
//    val member = memberService.getMemberByEmail(email)
//    if (!member.isValid() || !memberService.isValidJwtToken(accessToken)) {
//      return null
//    }
//
//    return CustomAu
//  }
}