package org.radon.cargoamigo.common.exceptionHandling

import org.springframework.http.HttpStatus

class DuplicateUserException: ExceptionModel("User already exists!", HttpStatus.CONFLICT)