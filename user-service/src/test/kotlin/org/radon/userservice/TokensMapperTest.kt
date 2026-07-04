package org.radon.userservice

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.radon.cargoamigo.common.UserType
import org.radon.cargoamigo.common.exceptionHandling.FieldMustNotBeEmptyException
import org.radon.userservice.domain.Tokens
import org.radon.userservice.presentation.dto.SignupRequest
import org.radon.userservice.presentation.dto.mapper.AuthMappers
import org.slf4j.LoggerFactory

class TokensMapperTest {

    var tokens = Tokens("alireza.kh.kh","fdfs65d4f.+s5fs+e.5+sef")
    val logger = LoggerFactory.getLogger(TokensMapperTest::class.java)

    @Test
    fun `should throw on first name`() {
        assertDoesNotThrow {
            val res = AuthMappers.convertTokensToLoginResponse(tokens)
            logger.debug("AccessToken: ${res.accessToken} / Refresh: ${res.refreshToken}")
        }
    }

}