package org.radon.cargoamigo.auth.infrastructure

import org.radon.cargoamigo.auth.application.port.out.AuthRepository
import org.radon.cargoamigo.auth.presentation.LoginRequest
import org.radon.cargoamigo.auth.presentation.LoginResponse
import org.radon.cargoamigo.auth.presentation.SignupRequest
import org.radon.cargoamigo.common.Response
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository

@Repository
class AuthRepository : AuthRepository {
    override fun login(request: LoginRequest): ResponseEntity<Response<LoginResponse>> {
        TODO("Not yet implemented")
    }

    override fun signup(request: SignupRequest): ResponseEntity<Response<LoginResponse>> {
        TODO("Not yet implemented")
    }

    override fun logout(): ResponseEntity<Response<String>> {
        TODO("Not yet implemented")
    }
}