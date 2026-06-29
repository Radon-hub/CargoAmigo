package org.radon.application.user.common.exceptionHandling

import org.radon.cargoamigo.common.exceptionHandling.ExceptionModel
import org.springframework.http.HttpStatus

class PasswordMismatchException: ExceptionModel("Password and confirmation are not same!", HttpStatus.BAD_REQUEST) {
}