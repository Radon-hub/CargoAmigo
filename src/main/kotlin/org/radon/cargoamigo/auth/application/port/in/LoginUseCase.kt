package org.radon.cargoamigo.auth.application.port.`in`

import org.radon.cargoamigo.auth.domain.Tokens
import org.radon.cargoamigo.auth.presentation.dto.LoginRequest
import org.radon.cargoamigo.auth.presentation.dto.LoginResponse
import org.radon.cargoamigo.common.Response
import org.springframework.http.ResponseEntity

interface LoginUseCase {
    fun login(request: LoginRequest?): Tokens
}