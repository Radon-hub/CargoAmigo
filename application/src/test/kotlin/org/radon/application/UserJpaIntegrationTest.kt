package org.radon.application


import org.junit.jupiter.api.*
import org.radon.cargoamigo.common.UserType
import org.radon.cargoamigo.common.exceptionHandling.DuplicateUserException
import org.radon.userservice.application.port.`in`.SignupUseCase
import org.radon.userservice.infrastructure.jpa.UserJpaRepository
import org.radon.userservice.presentation.dto.SignupRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.jvm.optionals.getOrNull
import kotlin.test.Test
import kotlin.test.assertEquals

@SpringBootTest(classes = [CargoAmigoApplication::class])
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class UserJpaIntegrationTest(
    @Autowired
    val repository: UserJpaRepository,
    @Autowired
    val signupUseCase: SignupUseCase
) {

    @Test
    @Order(1)
    fun `should save and see user`(){


        val result = repository.findUserByPhoneNumber("0912345678")

        println("***FROM SAVE****")

        println("find user : ${result}")

        if (result.isEmpty) {
            println("User not exists...")
            assertNull(result.getOrNull())
        }else{
            println("User exists ...")
            assertEquals("alirezza", result.get().firstName)
        }

    }

    @Test
    @Order(2)
    fun `should save & see & delete user`() {

        assertThrows<DuplicateUserException>{
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
        }

        println("***FROM DELETE****")
        val result = repository.findUserByPhoneNumber("0912345678")

        println("Find user : $result")

        if(result.isPresent){
            repository.delete(result.get())
            println("User deleted!")
            assert(true)
        }else{
            println("User not found.")
            assertNull(result.getOrNull())
        }

    }




}