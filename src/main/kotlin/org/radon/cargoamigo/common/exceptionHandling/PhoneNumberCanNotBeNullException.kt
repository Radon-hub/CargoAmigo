package org.radon.cargoamigo.common.exceptionHandling

import org.springframework.http.HttpStatus

class PhoneNumberCanNotBeNullException: ExceptionModel("Phone number can not be null!", HttpStatus.BAD_REQUEST)