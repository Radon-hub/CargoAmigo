package org.radon.cargoamigo.common.exceptionHandling

import org.springframework.http.HttpStatus

class CargoStatusCanNotBeChanged:ExceptionModel("Cargo status can not be changed!", HttpStatus.BAD_REQUEST) {
}