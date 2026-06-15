package org.radon.cargoamigo.common.Exception

import org.radon.cargoamigo.common.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.lang.RuntimeException

class ExceptionModel(override val message:String, val status: HttpStatus) : RuntimeException(message) {
    fun makeErrorResponse(path: String): ResponseEntity<ErrorResponse> = ResponseEntity.status(status).body(ErrorResponse(
            status = status.value(),
            error = status.name,
            path = path,
            message = message
        )
    )
}