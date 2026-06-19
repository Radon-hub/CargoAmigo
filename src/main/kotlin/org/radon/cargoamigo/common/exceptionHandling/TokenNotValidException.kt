package org.radon.cargoamigo.common.exceptionHandling

import org.springframework.http.HttpStatus

class TokenNotValidException: ExceptionModel(message = "Token is not valid!", HttpStatus.BAD_REQUEST) {
}