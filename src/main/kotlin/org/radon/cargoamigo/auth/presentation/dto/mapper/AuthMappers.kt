package org.radon.cargoamigo.auth.presentation.dto.mapper

import org.radon.cargoamigo.auth.domain.Tokens
import org.radon.cargoamigo.auth.presentation.dto.LoginResponse

object AuthMappers {
    fun convertTokensToLoginResponse(tokens: Tokens): LoginResponse = LoginResponse(
        tokens.accessToken,tokens.refreshToken,
    )
}