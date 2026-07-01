package org.radon.userservice.presentation.dto

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String
)
