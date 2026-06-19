package org.radon.cargoamigo.auth.application.port.`in`

import org.radon.cargoamigo.auth.domain.Tokens
import org.radon.cargoamigo.auth.presentation.dto.RefreshTokenRequest

interface RefreshTokenUseCase {
    fun refreshToken(request: RefreshTokenRequest?): Tokens
}