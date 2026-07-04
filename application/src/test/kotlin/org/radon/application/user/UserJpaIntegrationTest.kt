package org.radon.application.user


import org.junit.jupiter.api.*
import org.radon.application.BaseIntegrationTest
import org.radon.application.CargoAmigoApplication
import org.radon.userservice.infrastructure.jpa.UserJpaRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.jvm.optionals.getOrNull
import kotlin.test.Test
import kotlin.test.assertEquals

@SpringBootTest(classes = [CargoAmigoApplication::class])
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class UserJpaIntegrationTest(
    @Autowired val repo: UserJpaRepository
): BaseIntegrationTest() {
    val logger = LoggerFactory.getLogger(UserJpaIntegrationTest::class.java)


    @Test
    @Order(1)
    fun `should save and see user`(){


        val result = repo.findUserByPhoneNumber("0912345678")

        logger.info("***FROM SAVE****")

        logger.info("find user : ${result}")

        if (result.isEmpty) {
            logger.info("User not exists...")
            assertNull(result.getOrNull())
        }else{
            logger.info("User exists ...")
            assertEquals("alirezza", result.get().firstName)
        }

    }

    @Test
    @Order(2)
    fun `should save & see & delete user`() {

        logger.info("***FROM DELETE****")
        val result = repo.findUserByPhoneNumber("0912345678")

        logger.info("Find user : $result")

        if(result.isPresent){
            repo.delete(result.get())
            logger.info("User deleted!")
            assert(true)
        }else{
            logger.info("User not found.")
            assertNull(result.getOrNull())
        }

    }




}