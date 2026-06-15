package org.radon.cargoamigo.auth.presentation

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController {


    fun login(request: LoginRequest): LoginResponse? {return null}

    fun singUp(request: SignupRequest): LoginResponse? {return null}

    fun logOut(request: LoginRequest): LoginResponse? {return null}
}