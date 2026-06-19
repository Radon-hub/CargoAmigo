package org.radon.cargoamigo.common.exceptionHandling

import org.springframework.http.HttpStatus

class PasswordMismatchException: ExceptionModel("Password and confirmation are not same!", HttpStatus.BAD_REQUEST) {
}