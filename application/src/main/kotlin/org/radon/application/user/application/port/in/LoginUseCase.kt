package org.radon.application.user.application.port.`in`

import org.radon.application.user.presentation.dto.LoginRequest
import org.radon.application.user.domain.Tokens

interface LoginUseCase {
    fun login(request: LoginRequest?): Tokens
}