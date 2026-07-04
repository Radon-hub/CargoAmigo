package org.radon.userservice

import org.junit.jupiter.api.assertDoesNotThrow
import org.radon.userservice.presentation.dto.LoginRequest
import org.slf4j.LoggerFactory
import kotlin.test.Test

class LoginRequestTest {
    var loginRequest = LoginRequest("alireza","13254")
    val logger = LoggerFactory.getLogger(LoginRequestTest::class.java)


    @Test
    fun loginResponseGet() {
        assertDoesNotThrow {
            logger.debug("Request : ${loginRequest.username} / ${loginRequest.password}")
        }
    }
    @Test
    fun loginResponseSet() {
        assertDoesNotThrow {
            loginRequest = LoginRequest("jhon","1655")
        }
    }
}