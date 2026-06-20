package org.radon.cargoamigo.auth.db

import org.junit.jupiter.api.Assertions
import org.radon.cargoamigo.auth.application.port.`in`.SignupUseCase
import org.radon.cargoamigo.auth.infrastructure.jpa.UserJpaRepository
import org.radon.cargoamigo.auth.presentation.dto.SignupRequest
import org.radon.cargoamigo.common.UserType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@SpringBootTest
class UserJpaRepositoryTest(
    @Autowired
    val repository: UserJpaRepository,
    @Autowired
    val signupUseCase: SignupUseCase
) {


    @Test
    fun `should save user & get user & delete & assert it is not present anymore`() {

        signupUseCase.signup(
            SignupRequest(
                "alirezza",
                "test",
                "0912345678",
                "1234",
                "1234",
                45,
                UserType.EMPLOYER
            )
        )

        var result = repository.findUserByPhoneNumber("0912345678")

        println("find user : $result")

        Assertions.assertNotNull(result)

        assertEquals("alirezza", result.get().firstName)

        repository.delete(result.get())

        println("user deleted")

        result = repository.findUserByPhoneNumber("0912345678")

        println("find user : $result")

        assertTrue(result.isEmpty)

        println("Finish JPA test.")

    }

}