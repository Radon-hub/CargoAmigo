package org.radon.userservice


import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.radon.cargoamigo.common.UserType
import org.radon.cargoamigo.common.exceptionHandling.FieldMustNotBeEmptyException
import org.radon.cargoamigo.common.exceptionHandling.InvalidAgeException
import org.radon.cargoamigo.common.exceptionHandling.PhoneNumberCanNotBeNullException
import org.radon.userservice.common.exceptionHandling.PasswordMismatchException
import org.radon.userservice.presentation.dto.SignupRequest
import org.slf4j.LoggerFactory

class SignupRequestTest {

    var singUpModel = SignupRequest("", "", "", "", "", 0, UserType.EMPLOYER)
    val logger = LoggerFactory.getLogger(SignupRequestTest::class.java)

    @Test
    fun `should throw on first name`() {
        assertThrows(FieldMustNotBeEmptyException::class.java){
            singUpModel.validateRequest()
        }
        logger.info("FieldMustNotBeEmptyException happen on first name = empty")
    }

    @Test
    fun `should throw on last name`() {
        assertThrows(FieldMustNotBeEmptyException::class.java){
            singUpModel.copy("Jon").validateRequest()
        }
        logger.info("FieldMustNotBeEmptyException happen on last name = empty")
    }

    @Test
    fun `should throw on phone number`() {
        assertThrows(PhoneNumberCanNotBeNullException::class.java){
            singUpModel.copy("Jon","Smith").validateRequest()
        }
        logger.info("PhoneNumberCanNotBeNullException happen on phone number = empty")
    }

    @Test
    fun `should throw on password`() {
        assertThrows(FieldMustNotBeEmptyException::class.java){
            singUpModel.copy("Jon","Smith","0214558565").validateRequest()
        }
        logger.info("FieldMustNotBeEmptyException happen on password = empty")
    }

    @Test
    fun `should throw on password confirmation`() {
        assertThrows(PasswordMismatchException::class.java){
            singUpModel.copy("Jon","Smith","0214558565","1234").validateRequest()
        }
        logger.info("PasswordMismatchException happen on password mismatch")
    }

    @Test
    fun `should throw on age`() {
        assertThrows(InvalidAgeException::class.java){
            singUpModel.copy("Jon","Smith","0214558565","1234","1234").validateRequest()
        }
        logger.info("InvalidAgeException happen on age")
    }

    @Test
    fun `should not throw anything`() {
        assertDoesNotThrow {
            singUpModel.copy(
                "Jon",
                "Smith",
                "0214558565",
                "1234",
                "1234",
                21
            ).validateRequest()
        }
        logger.info("Request is perfect.")
    }

}