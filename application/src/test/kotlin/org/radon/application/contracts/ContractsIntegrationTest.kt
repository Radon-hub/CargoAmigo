package org.radon.application.contracts

import dto.UserContractDto
import lombok.extern.slf4j.Slf4j
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.assertNotNull
import org.radon.application.CargoAmigoApplication
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import port.GetUserWithIdUseCase
import port.GetUserWithPhoneNumberUseCase
import kotlin.test.Test

@SpringBootTest(classes = [CargoAmigoApplication::class])
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@Slf4j
class ContractsIntegrationTest(
    @Autowired val getUserWithIdUseCase: GetUserWithIdUseCase,
    @Autowired val getUserWithPhoneNumberUseCase: GetUserWithPhoneNumberUseCase
) {

    companion object {
        lateinit var user: UserContractDto
        val logger = LoggerFactory.getLogger(CargoAmigoApplication::class.java)
    }


    @Test
    @Order(1)
    fun `Getting user with phone number`() {
        logger.info("Starting the getUserWithPhone contract...")
        user = getUserWithPhoneNumberUseCase.getWithPhoneNumber("09369101332")
        assertEquals(user.phoneNumber, "09369101332")
        logger.info("User ${user.id} with phone number ${user.phoneNumber} is == 09369101332")
        logger.info("Contract finished!")
    }

    @Test
    @Order(2)
    fun `Getting user with id`() {
        logger.info("Starting the getUserWithID contract...")
        assertNotNull(user.id)
        val fetchedUser = getUserWithIdUseCase.getWithId(user.id!!)
        assertEquals(user.id, fetchedUser.id)
        logger.info("User ${user.id} is fetched!")
        logger.info("Contract finished!")
    }




}