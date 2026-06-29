package org.radon.application.user.common.exceptionHandling

import org.radon.cargoamigo.common.exceptionHandling.ExceptionModel
import org.springframework.http.HttpStatus

class WrongPasswordException: ExceptionModel("Password is incorrect!", HttpStatus.UNAUTHORIZED) {
}