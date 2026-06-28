package org.radon.cargoamigo.common.exceptionHandling

import org.springframework.http.HttpStatus

class CargoNotBelongsToUserException: ExceptionModel("Cargo not belongs to this user!", HttpStatus.BAD_REQUEST) {
}