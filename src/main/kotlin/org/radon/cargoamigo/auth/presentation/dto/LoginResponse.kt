package org.radon.cargoamigo.auth.presentation.dto

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String
)
