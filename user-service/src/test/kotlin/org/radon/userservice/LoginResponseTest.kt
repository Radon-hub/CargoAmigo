package org.radon.userservice

import org.junit.jupiter.api.assertDoesNotThrow
import org.radon.userservice.presentation.dto.LoginResponse
import org.radon.userservice.presentation.dto.RefreshTokenRequest
import org.slf4j.LoggerFactory
import kotlin.test.Test

class LoginResponseTest {
    var loginResponse = LoginResponse("","")
    val logger = LoggerFactory.getLogger(LoginResponseTest::class.java)


    @Test
    fun loginResponseGet() {
        assertDoesNotThrow {
            logger.debug("Reponse : ${loginResponse.refreshToken} / ${loginResponse.accessToken}")
        }
    }
    @Test
    fun loginResponseSet() {
        assertDoesNotThrow {
            loginResponse = LoginResponse("5a4dfa5s4df5as4df.asdfa6e5f1a.afes5a4f5","5a4dfa5s4df5as4df.asdfa6e5f1a.afes5a4f5")
        }
    }
}