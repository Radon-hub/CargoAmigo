package org.radon.cargoamigo.auth.application.port.`in`

import org.radon.cargoamigo.auth.presentation.LoginResponse
import org.radon.cargoamigo.auth.presentation.SignupRequest
import org.radon.cargoamigo.common.Response
import org.springframework.http.ResponseEntity

interface SignupUseCase {
    fun signup(request: SignupRequest): ResponseEntity<Response<LoginResponse>>
}