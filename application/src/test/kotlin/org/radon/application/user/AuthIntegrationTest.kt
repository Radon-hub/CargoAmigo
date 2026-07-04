package org.radon.application.user


import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.TestMethodOrder
import org.radon.application.BaseIntegrationTest
import org.radon.application.CargoAmigoApplication
import org.radon.userservice.domain.Tokens
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import tools.jackson.databind.ObjectMapper
import kotlin.test.Test
import kotlin.test.assertNotNull

@SpringBootTest(classes = [CargoAmigoApplication::class])
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@AutoConfigureMockMvc
class AuthIntegrationTest(
    @Autowired val mockMvc: MockMvc,
    @Autowired val objectMapper: ObjectMapper
): BaseIntegrationTest() {

    companion object {
        lateinit var token: Tokens
        val logger = LoggerFactory.getLogger(AuthIntegrationTest::class.java)
    }

    @Test
    @Order(1)
    fun `should login successfully`() {

        logger.info("************ Starting login flow ***************")
        val result = mockMvc.perform(
            post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                            {
                                "username":"09369101332",
                                "password":"123456789"
                            }
                        """)
        ).andExpect(status().isOk).andReturn()

        val json = objectMapper.readTree(result.response.contentAsString)

        token = Tokens(
            accessToken = json["data"]["accessToken"].toString(),
            refreshToken = json["data"]["refreshToken"].toString()
        )

        logger.info("Getting tokens from login : $token")

        assertNotNull(token)

        logger.info("************ login flow ***************")

    }


    @Test
    @Order(2)
    fun `should refresh token successfully`() {

        logger.info("************ Starting refresh token flow ***************")

        val result = mockMvc.perform(
            post("/auth/refresh-token")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                            {
                                "refreshToken":${token.refreshToken}
                            }
                        """)
        ).andExpect(status().isOk).andReturn()

        val json = objectMapper.readTree(result.response.contentAsString)

        token = Tokens(
            accessToken = json["data"]["accessToken"].toString(),
            refreshToken = json["data"]["refreshToken"].toString()
        )

        logger.info("Getting tokens from refresh token : $token")

        assertNotNull(token)

        logger.info("************ refresh token flow ***************")

    }

}