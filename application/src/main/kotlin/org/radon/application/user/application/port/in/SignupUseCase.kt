package org.radon.application.user.application.port.`in`

import org.radon.application.user.presentation.dto.SignupRequest
import org.radon.application.user.domain.Tokens

interface SignupUseCase {
    fun signup(request: SignupRequest?): Tokens
}