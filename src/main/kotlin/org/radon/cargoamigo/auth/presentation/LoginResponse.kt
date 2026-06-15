package org.radon.cargoamigo.auth.presentation

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String
)
