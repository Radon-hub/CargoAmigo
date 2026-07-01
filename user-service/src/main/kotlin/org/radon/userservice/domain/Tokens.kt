package org.radon.userservice.domain

data class Tokens(
    val accessToken: String,
    val refreshToken: String
)
