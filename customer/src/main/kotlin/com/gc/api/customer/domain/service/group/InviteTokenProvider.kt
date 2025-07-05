package com.gc.api.customer.domain.service.group

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*
import javax.crypto.SecretKey

@Service
class InviteTokenProvider(
    @Value("\${jwt.invite}") private val secretKey: String,
) {

    private val key: SecretKey = Keys.hmacShaKeyFor(secretKey.toByteArray())
    private val EXPIRE_DAY: Long = 7 * 24 * 60 * 60


    fun generateInviteToken(groupId: String): String {
        val now = Date()
        val expiry = Date(now.time + EXPIRE_DAY)

        return Jwts.builder()
            .claim("groupId", groupId)
            .issuedAt(now)
            .expiration(expiry)
            .signWith(key)
            .compact()
    }

    fun parseInviteToken(token: String): String? {
        val payload = Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .payload

        return payload["groupId"].toString()
    }
}