package org.radon.userservice.common.exceptionHandling

import org.radon.cargoamigo.common.exceptionHandling.ExceptionModel
import org.springframework.http.HttpStatus

class WrongPasswordException: ExceptionModel("Password is incorrect!", HttpStatus.UNAUTHORIZED) {
}