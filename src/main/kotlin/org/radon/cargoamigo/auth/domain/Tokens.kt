package org.radon.cargoamigo.auth.domain

data class Tokens(
    val accessToken: String,
    val refreshToken: String
)
