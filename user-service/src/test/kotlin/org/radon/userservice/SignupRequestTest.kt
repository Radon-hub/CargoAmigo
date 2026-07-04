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
        logger.debug("FieldMustNotBeEmptyException happen on first name = empty")
    }

    @Test
    fun `should throw on last name`() {
        assertThrows(FieldMustNotBeEmptyException::class.java){
            singUpModel.copy("Jon").validateRequest()
        }
        logger.debug("FieldMustNotBeEmptyException happen on last name = empty")
    }

    @Test
    fun `should throw on phone number`() {
        assertThrows(PhoneNumberCanNotBeNullException::class.java){
            singUpModel.copy("Jon","Smith").validateRequest()
        }
        logger.debug("PhoneNumberCanNotBeNullException happen on phone number = empty")
    }

    @Test
    fun `should throw on password`() {
        assertThrows(FieldMustNotBeEmptyException::class.java){
            singUpModel.copy("Jon","Smith","0214558565").validateRequest()
        }
        logger.debug("FieldMustNotBeEmptyException happen on password = empty")
    }

    @Test
    fun `should throw on password confirmation`() {
        assertThrows(PasswordMismatchException::class.java){
            singUpModel.copy("Jon","Smith","0214558565","1234").validateRequest()
        }
        logger.debug("PasswordMismatchException happen on password mismatch")
    }

    @Test
    fun `should throw on age`() {
        assertThrows(InvalidAgeException::class.java){
            singUpModel.copy("Jon","Smith","0214558565","1234","1234").validateRequest()
        }
        logger.debug("InvalidAgeException happen on age")
    }

    @Test
    fun `should throw on age bigger`() {
        assertThrows(InvalidAgeException::class.java){
            singUpModel.copy("Jon","Smith","0214558565","1234","1234",100).validateRequest()
        }
        logger.debug("InvalidAgeException happen on age")
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
        logger.debug("Request is perfect.")
    }


    @Test
    fun `should not throw anything on get`() {
        assertDoesNotThrow {
            logger.debug("firstname " + singUpModel.firstname)
            logger.debug("lastname " + singUpModel.lastname)
            logger.debug("phone " + singUpModel.phone)
            logger.debug("password " + singUpModel.password)
            logger.debug("passwordConfirmation " + singUpModel.passwordConfirmation)
            logger.debug("age " + singUpModel.age)
            logger.debug("type " + singUpModel.type)
        }
        logger.debug("Request is perfect with gets.")
    }
    @Test
    fun `should not throw anything on set`() {
        assertDoesNotThrow {
            singUpModel.firstname = ""
            singUpModel.lastname = ""
            singUpModel.phone = ""
            singUpModel.password = ""
            singUpModel.passwordConfirmation = ""
            singUpModel.age = 12
            singUpModel.type = UserType.EMPLOYER
        }
        logger.debug("Request is perfect with sets.")
    }


}