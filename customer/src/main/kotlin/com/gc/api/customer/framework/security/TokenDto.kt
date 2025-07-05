package com.gc.api.customer.framework.security

data class TokenDto(
    val accessToken: String,
    val refreshToken: String?,
) {
}