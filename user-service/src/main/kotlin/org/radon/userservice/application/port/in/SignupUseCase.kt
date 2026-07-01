package org.radon.userservice.application.port.`in`

import org.radon.userservice.presentation.dto.SignupRequest
import org.radon.userservice.domain.Tokens

interface SignupUseCase {
    fun signup(request: SignupRequest?): Tokens
}