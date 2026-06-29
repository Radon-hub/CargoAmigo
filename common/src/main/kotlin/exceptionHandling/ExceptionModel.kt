package org.radon.cargoamigo.common.exceptionHandling

import org.radon.cargoamigo.common.ErrorResponse
import org.radon.cargoamigo.common.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

open class ExceptionModel(override val message:String, val status: HttpStatus) : RuntimeException(message) {
    fun makeErrorResponse(path: String): ResponseEntity<Response<Nothing?>> = ResponseEntity.status(status).body(
        Response(
            data = null, error =
                ErrorResponse(
                    status = status.value(),
                    error = status.name,
                    path = path,
                    message = message
                )
        )
    )
}