package org.radon.cargoamigo.auth.presentation


import org.radon.cargoamigo.auth.application.port.`in`.LoginUseCase
import org.radon.cargoamigo.auth.application.port.`in`.RefreshTokenUseCase
import org.radon.cargoamigo.auth.application.port.`in`.SignupUseCase
import org.radon.cargoamigo.auth.presentation.dto.LoginRequest
import org.radon.cargoamigo.auth.presentation.dto.LoginResponse
import org.radon.cargoamigo.auth.presentation.dto.RefreshTokenRequest
import org.radon.cargoamigo.auth.presentation.dto.SignupRequest
import org.radon.cargoamigo.auth.presentation.dto.mapper.AuthMappers
import org.radon.cargoamigo.common.Response
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val signUpUseCase: SignupUseCase,
    private val loginUseCase: LoginUseCase,
    private val refreshTokenUseCase: RefreshTokenUseCase
) {

    @PostMapping("register")
    fun singUp(@RequestBody request: SignupRequest): ResponseEntity<Response<LoginResponse>> {
        val result = signUpUseCase.signup(request)
        return ResponseEntity.ok(Response(AuthMappers.convertTokensToLoginResponse(result)))
    }

    @PostMapping("login")
        fun login(@RequestBody request: LoginRequest): ResponseEntity<Response<LoginResponse>> {
        val result = loginUseCase.login(request)
        return ResponseEntity.ok(Response(AuthMappers.convertTokensToLoginResponse(result)))
    }

    @PostMapping("refresh-token")
    fun refreshToken(@RequestBody request: RefreshTokenRequest): ResponseEntity<Response<LoginResponse>> {
        val result = refreshTokenUseCase.refreshToken(request)
        return ResponseEntity.ok(Response(AuthMappers.convertTokensToLoginResponse(result)))
    }

}