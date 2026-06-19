package org.radon.cargoamigo.common.exceptionHandling

import org.springframework.http.HttpStatus

class WrongPasswordException: ExceptionModel("Password is incorrect!", HttpStatus.UNAUTHORIZED) {
}