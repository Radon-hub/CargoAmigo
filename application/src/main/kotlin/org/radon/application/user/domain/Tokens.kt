package org.radon.application.user.domain

data class Tokens(
    val accessToken: String,
    val refreshToken: String
)
