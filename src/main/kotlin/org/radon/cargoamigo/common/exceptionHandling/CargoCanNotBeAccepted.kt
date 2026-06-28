package org.radon.cargoamigo.common.exceptionHandling

import org.springframework.http.HttpStatus

class CargoCanNotBeAccepted: ExceptionModel("Cargo can not be accepted!", HttpStatus.BAD_REQUEST) {
}