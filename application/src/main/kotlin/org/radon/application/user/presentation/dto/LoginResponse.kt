package org.radon.application.user.presentation.dto

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String
)
