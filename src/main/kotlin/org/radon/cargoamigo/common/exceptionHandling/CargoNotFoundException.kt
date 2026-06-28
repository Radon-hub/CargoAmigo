package org.radon.cargoamigo.common.exceptionHandling

import org.springframework.http.HttpStatus

class CargoNotFoundException:ExceptionModel("No cargo found with this code!", HttpStatus.NOT_FOUND) {
}