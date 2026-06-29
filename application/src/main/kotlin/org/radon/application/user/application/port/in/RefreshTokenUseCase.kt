package org.radon.application.user.application.port.`in`

import org.radon.application.user.presentation.dto.RefreshTokenRequest
import org.radon.application.user.domain.Tokens

interface RefreshTokenUseCase {
    fun refreshToken(request: RefreshTokenRequest?): Tokens
}