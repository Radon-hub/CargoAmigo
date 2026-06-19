package org.radon.cargoamigo.common.exceptionHandling

import org.springframework.http.HttpStatus

class InvalidAgeException: ExceptionModel("Age must be between 18 and 90!", HttpStatus.BAD_REQUEST) {
}