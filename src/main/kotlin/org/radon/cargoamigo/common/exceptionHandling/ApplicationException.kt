package org.radon.cargoamigo.common.exceptionHandling

import org.springframework.http.HttpStatus

class ApplicationException(cause: String): ExceptionModel(cause, HttpStatus.INTERNAL_SERVER_ERROR) {
}