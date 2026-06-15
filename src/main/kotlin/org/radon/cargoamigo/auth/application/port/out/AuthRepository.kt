package org.radon.cargoamigo.auth.application.port.out

import org.radon.cargoamigo.auth.presentation.LoginRequest
import org.radon.cargoamigo.auth.presentation.LoginResponse
import org.radon.cargoamigo.auth.presentation.SignupRequest
import org.radon.cargoamigo.common.Response
import org.springframework.http.ResponseEntity

interface AuthRepository {
    fun login(request: LoginRequest): ResponseEntity<Response<LoginResponse>>
    fun signup(request: SignupRequest): ResponseEntity<Response<LoginResponse>>
    fun logout(): ResponseEntity<Response<String>>
}