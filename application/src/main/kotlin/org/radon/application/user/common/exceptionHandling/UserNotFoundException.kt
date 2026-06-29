package org.radon.application.user.common.exceptionHandling

import org.radon.cargoamigo.common.exceptionHandling.ExceptionModel
import org.springframework.http.HttpStatus

class UserNotFoundException: ExceptionModel("User not found!", HttpStatus.BAD_REQUEST) {
}