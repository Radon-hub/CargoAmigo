package org.radon.cargoamigo.common.exceptionHandling

import org.springframework.http.HttpStatus

class CargoCodeCanNotBeNullException: ExceptionModel("Cargo code can not be empty or null!", HttpStatus.BAD_REQUEST) {
}