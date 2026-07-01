package org.radon.application


import org.junit.jupiter.api.Order
import org.radon.userservice.domain.Tokens
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.persistence.autoconfigure.EntityScan
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import tools.jackson.databind.ObjectMapper
import kotlin.test.Test
import kotlin.test.assertNotNull

@SpringBootTest(classes = [CargoAmigoApplication::class])
@AutoConfigureMockMvc
class SignupIntegrationTest(
    @Autowired val mockMvc: MockMvc,
    @Autowired val objectMapper: ObjectMapper
) {

    companion object {
        lateinit var token: Tokens
    }


    @Test
    @Order(1)
    fun `should login successfully`() {

        println("************ Starting login flow ***************")
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

        println("Getting tokens from login : $token")

        assertNotNull(token)

        println("************ login flow ***************")

    }


    @Test
    @Order(2)
    fun `should refresh token successfully`() {

        println("************ Starting refresh token flow ***************")

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

        println("Getting tokens from refresh token : $token")

        assertNotNull(token)

        println("************ refresh token flow ***************")

    }

}