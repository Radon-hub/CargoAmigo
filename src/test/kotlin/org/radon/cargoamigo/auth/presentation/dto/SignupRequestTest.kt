package org.radon.cargoamigo.auth.presentation.dto

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.radon.cargoamigo.common.UserType
import org.radon.cargoamigo.common.exceptionHandling.FieldMustNotBeEmptyException
import org.radon.cargoamigo.common.exceptionHandling.InvalidAgeException
import org.radon.cargoamigo.common.exceptionHandling.PasswordMismatchException
import org.radon.cargoamigo.common.exceptionHandling.PhoneNumberCanNotBeNullException

class SignupRequestTest {

    var singUpModel = SignupRequest("","","","","",0, UserType.EMPLOYER)

    @Test
    fun `should throw on first name`() {
        assertThrows(FieldMustNotBeEmptyException::class.java){
            singUpModel.validateRequest()
        }
        println("FieldMustNotBeEmptyException happen on first name = empty")
    }

    @Test
    fun `should throw on last name`() {
        assertThrows(FieldMustNotBeEmptyException::class.java){
            singUpModel.copy("Jon").validateRequest()
        }
        println("FieldMustNotBeEmptyException happen on last name = empty")
    }

    @Test
    fun `should throw on phone number`() {
        assertThrows(PhoneNumberCanNotBeNullException::class.java){
            singUpModel.copy("Jon","Smith").validateRequest()
        }
        println("PhoneNumberCanNotBeNullException happen on phone number = empty")
    }

    @Test
    fun `should throw on password`() {
        assertThrows(FieldMustNotBeEmptyException::class.java){
            singUpModel.copy("Jon","Smith","0214558565").validateRequest()
        }
        println("FieldMustNotBeEmptyException happen on password = empty")
    }

    @Test
    fun `should throw on password confirmation`() {
        assertThrows(PasswordMismatchException::class.java){
            singUpModel.copy("Jon","Smith","0214558565","1234").validateRequest()
        }
        println("PasswordMismatchException happen on password mismatch")
    }

    @Test
    fun `should throw on age`() {
        assertThrows(InvalidAgeException::class.java){
            singUpModel.copy("Jon","Smith","0214558565","1234","1234").validateRequest()
        }
        println("InvalidAgeException happen on age")
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
        println("Request is perfect.")
    }

}