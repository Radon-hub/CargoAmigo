package org.radon.cargoamigo.common.exceptionHandling

import org.springframework.http.HttpStatus

class CargoCanNotBeAdded: ExceptionModel("Faild to add cargo ... please try again later!", HttpStatus.INTERNAL_SERVER_ERROR) {
}