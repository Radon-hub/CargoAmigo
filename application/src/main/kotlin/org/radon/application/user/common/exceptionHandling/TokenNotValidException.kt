package org.radon.application.user.common.exceptionHandling

import org.radon.cargoamigo.common.exceptionHandling.ExceptionModel
import org.springframework.http.HttpStatus

class TokenNotValidException: ExceptionModel(message = "Token is not valid!", HttpStatus.BAD_REQUEST) {
}