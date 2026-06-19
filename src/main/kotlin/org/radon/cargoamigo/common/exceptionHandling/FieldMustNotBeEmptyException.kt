package org.radon.cargoamigo.common.exceptionHandling

import org.springframework.http.HttpStatus

class FieldMustNotBeEmptyException(fieldName: String): ExceptionModel("$fieldName Must not be empty!", HttpStatus.BAD_REQUEST) {
}