package org.radon.cargoamigo.common.exceptionHandling

import org.springframework.http.HttpStatus

class DataBaseException(message: String) : ExceptionModel(message, HttpStatus.INTERNAL_SERVER_ERROR)