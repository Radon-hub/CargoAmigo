package org.radon.cargoamigo.auth.application.port.`in`

import org.radon.cargoamigo.auth.presentation.LoginRequest
import org.radon.cargoamigo.auth.presentation.LoginResponse
import org.radon.cargoamigo.common.Response
import org.springframework.http.ResponseEntity

interface LoginUseCase {
    fun login(request: LoginRequest): ResponseEntity<Response<LoginResponse>>
}