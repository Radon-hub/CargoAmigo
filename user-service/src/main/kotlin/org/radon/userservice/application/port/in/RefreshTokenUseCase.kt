package org.radon.userservice.application.port.`in`

import org.radon.userservice.presentation.dto.RefreshTokenRequest
import org.radon.userservice.domain.Tokens

interface RefreshTokenUseCase {
    fun refreshToken(request: RefreshTokenRequest?): Tokens
}