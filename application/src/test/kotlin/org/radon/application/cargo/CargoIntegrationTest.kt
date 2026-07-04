package org.radon.application.cargo

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.TestMethodOrder
import org.radon.application.BaseIntegrationTest
import org.radon.application.CargoAmigoApplication
import org.radon.application.user.AuthIntegrationTest
import org.radon.userservice.application.port.`in`.SignupUseCase
import org.radon.userservice.domain.Tokens
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import tools.jackson.databind.ObjectMapper
import kotlin.test.Test
import kotlin.test.assertNotNull

@SpringBootTest(classes = [CargoAmigoApplication::class])
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@AutoConfigureMockMvc
class CargoIntegrationTest(
    @Autowired val mockMvc: MockMvc,
    @Autowired val objectMapper: ObjectMapper,
): BaseIntegrationTest() {


    companion object {
        lateinit var token: Tokens
        val logger = LoggerFactory.getLogger(AuthIntegrationTest::class.java)
    }

    @Test
    @Order(1)
    fun `Should accessing to cargos denied because of none token`(){
        logger.info("Start unauthenticated access to cargo --------")
        val result = mockMvc.perform(
            get("/api/v1/cargo")
                .contentType(MediaType.APPLICATION_JSON)
                .content("")
        ).andExpect(status().isUnauthorized).andReturn()

        assert(result.response.status == 401)

        logger.info("Unauthorized access caught!")

    }

    @Test
    @Order(2)
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
            accessToken = json["data"]["accessToken"].stringValue(),
            refreshToken = json["data"]["refreshToken"].stringValue()
        )

        logger.info("Getting tokens from login : $token")

        assertNotNull(token)

        logger.info("************ login flow ***************")

    }

    @Test
    @Order(3)
    fun `Should accessing to cargos accepted because of token`(){
        logger.info("Start access to cargo --------")
        val result = mockMvc.perform(
            get("/api/v1/cargo/ACTIVE")
                .contentType(MediaType.APPLICATION_JSON)
                .content("")
                .header("Authorization", "Bearer ${token.accessToken}")
        ).andExpect(status().isOk).andReturn()

        val json = objectMapper.readTree(result.response.contentAsString)

        assertNotNull(json["data"]["content"])
        assert(json["data"]["numberOfElements"].intValue() > 0)

        logger.info("${json["data"]["content"]}")

        logger.info("Can access to cargo and search")

    }



}