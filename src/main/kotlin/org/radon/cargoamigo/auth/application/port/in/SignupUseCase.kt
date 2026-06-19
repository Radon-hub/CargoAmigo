package org.radon.cargoamigo.auth.application.port.`in`

import org.radon.cargoamigo.auth.domain.Tokens
import org.radon.cargoamigo.auth.presentation.dto.LoginResponse
import org.radon.cargoamigo.auth.presentation.dto.SignupRequest
import org.radon.cargoamigo.common.Response
import org.springframework.http.ResponseEntity

interface SignupUseCase {
    fun signup(request: SignupRequest?): Tokens
}