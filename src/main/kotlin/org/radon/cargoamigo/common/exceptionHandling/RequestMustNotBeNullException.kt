package org.radon.cargoamigo.common.exceptionHandling

import org.springframework.http.HttpStatus

class RequestMustNotBeNullException : ExceptionModel("Request must not be null!", HttpStatus.BAD_REQUEST)