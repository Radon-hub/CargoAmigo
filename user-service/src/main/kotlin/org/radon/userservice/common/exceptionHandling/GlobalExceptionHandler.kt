package org.radon.userservice.common.exceptionHandling

import jakarta.servlet.http.HttpServletRequest
import org.radon.cargoamigo.common.Response
import org.radon.cargoamigo.common.exceptionHandling.ExceptionModel
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(ExceptionModel::class)
    fun handleException(
        ex: ExceptionModel,
        request: HttpServletRequest?
    ): ResponseEntity<Response<Nothing?>> {
        return ex.makeErrorResponse(request?.requestURL.toString())
    }
}