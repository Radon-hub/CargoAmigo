package org.radon.userservice

import org.junit.jupiter.api.assertDoesNotThrow
import org.radon.userservice.presentation.dto.RefreshTokenRequest
import org.slf4j.LoggerFactory
import kotlin.test.Test

class RefreshTokenRequestTest {
    var refreshToken = RefreshTokenRequest("")
    val logger = LoggerFactory.getLogger(RefreshTokenRequestTest::class.java)


    @Test
    fun testRefreshTokenGet() {
        assertDoesNotThrow {
            logger.debug("refreshToken : ${refreshToken.refreshToken}")
        }
    }
    @Test
    fun testRefreshTokenSet() {
        assertDoesNotThrow {
            refreshToken = RefreshTokenRequest("5a4dfa5s4df5as4df.asdfa6e5f1a.afes5a4f5")
        }
    }
}