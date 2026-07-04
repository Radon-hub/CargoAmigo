package org.radon.application

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance
import org.radon.cargoamigo.common.UserType
import org.radon.userservice.application.port.`in`.SignupUseCase
import org.radon.userservice.infrastructure.jpa.UserJpaRepository
import org.radon.userservice.presentation.dto.SignupRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class BaseIntegrationTest {

    @Autowired
    lateinit var signupUseCase: SignupUseCase

    @Autowired
    lateinit var repository: UserJpaRepository

    @BeforeEach
    fun setup() {

        if (repository.findUserByPhoneNumber("09369101332").isEmpty) {
            signupUseCase.signup(
                SignupRequest(
                    "Ali",
                    "Test",
                    "09369101332",
                    "123456789",
                    "123456789",
                    25,
                    UserType.EMPLOYER
                )
            )
        }
    }
}