package org.radon.userservice.presentation.dto.mapper

import org.radon.userservice.domain.Tokens
import org.radon.userservice.presentation.dto.LoginResponse

object AuthMappers {
    fun convertTokensToLoginResponse(tokens: Tokens): LoginResponse = LoginResponse(
        tokens.accessToken,tokens.refreshToken,
    )
}