package org.radon.application.user.presentation.dto.mapper

import org.radon.application.user.domain.Tokens
import org.radon.application.user.presentation.dto.LoginResponse

object AuthMappers {
    fun convertTokensToLoginResponse(tokens: Tokens): LoginResponse = LoginResponse(
        tokens.accessToken,tokens.refreshToken,
    )
}