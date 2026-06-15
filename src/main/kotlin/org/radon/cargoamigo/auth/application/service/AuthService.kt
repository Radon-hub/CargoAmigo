package org.radon.cargoamigo.auth.application.service

import org.radon.cargoamigo.auth.application.port.`in`.LoginUseCase
import org.radon.cargoamigo.auth.application.port.`in`.LogoutUseCase
import org.radon.cargoamigo.auth.application.port.`in`.SignupUseCase
import org.radon.cargoamigo.auth.infrastructure.AuthRepository
import org.radon.cargoamigo.auth.presentation.LoginRequest
import org.radon.cargoamigo.auth.presentation.LoginResponse
import org.radon.cargoamigo.auth.presentation.SignupRequest
import org.radon.cargoamigo.common.Response
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class AuthService(private val repository: AuthRepository) : LoginUseCase, LogoutUseCase, SignupUseCase {

    override fun login(request: LoginRequest): ResponseEntity<Response<LoginResponse>> {
        return repository.login(request)
    }

    override fun logout(): ResponseEntity<Response<String>> {
        return repository.logout()
    }

    override fun signup(request: SignupRequest): ResponseEntity<Response<LoginResponse>> {
        return repository.signup(request)
    }
}