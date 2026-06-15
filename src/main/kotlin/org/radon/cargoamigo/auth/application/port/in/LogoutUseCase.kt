package org.radon.cargoamigo.auth.application.port.`in`

import org.radon.cargoamigo.common.Response
import org.springframework.http.ResponseEntity

interface LogoutUseCase {
    fun logout(): ResponseEntity<Response<String>>
}