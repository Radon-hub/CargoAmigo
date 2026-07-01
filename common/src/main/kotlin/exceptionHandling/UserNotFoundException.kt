package org.radon.cargoamigo.common.exceptionHandling

import org.springframework.http.HttpStatus

class UserNotFoundException: ExceptionModel("User not found!", HttpStatus.BAD_REQUEST) {
}