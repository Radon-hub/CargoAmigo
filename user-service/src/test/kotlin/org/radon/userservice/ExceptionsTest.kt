package org.radon.userservice

import org.junit.jupiter.api.assertNotNull
import org.junit.jupiter.api.assertThrows
import org.radon.cargoamigo.common.exceptionHandling.ExceptionModel
import org.radon.userservice.common.exceptionHandling.GlobalExceptionHandler
import org.radon.userservice.common.exceptionHandling.PasswordMismatchException
import org.radon.userservice.common.exceptionHandling.TokenNotValidException
import org.radon.userservice.common.exceptionHandling.WrongPasswordException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import kotlin.test.Test

class ExceptionsTest {
    val logger = LoggerFactory.getLogger(ExceptionsTest::class.java)

    @Test
    fun `test GlobalException`() {
        val exp = GlobalExceptionHandler().handleException(
            ExceptionModel("", HttpStatus.BAD_REQUEST),
            null,
        )

        assert(
            exp.statusCode == HttpStatus.BAD_REQUEST
        )

        assertNotNull(exp)

    }

    @Test
    fun `test PasswordMismatchException`() {
        assertThrows<ExceptionModel> { throw PasswordMismatchException() }
    }
    @Test
    fun `test TokenNotValidException`() {
        assertThrows<ExceptionModel> { throw TokenNotValidException() }
    }
    @Test
    fun `test WrongPasswordException`() {
        assertThrows<ExceptionModel> { throw WrongPasswordException() }
    }

}