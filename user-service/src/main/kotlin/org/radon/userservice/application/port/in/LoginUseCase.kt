package org.radon.userservice.application.port.`in`

import org.radon.userservice.presentation.dto.LoginRequest
import org.radon.userservice.domain.Tokens

interface LoginUseCase {
    fun login(request: LoginRequest?): Tokens
}