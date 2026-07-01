package org.radon.userservice.presentation.dto

import org.radon.cargoamigo.common.UserType
import org.radon.userservice.common.exceptionHandling.PasswordMismatchException
import org.radon.cargoamigo.common.exceptionHandling.FieldMustNotBeEmptyException
import org.radon.cargoamigo.common.exceptionHandling.InvalidAgeException
import org.radon.cargoamigo.common.exceptionHandling.PhoneNumberCanNotBeNullException
import org.radon.cargoamigo.common.isEmptyOrBlank

data class SignupRequest(
    var firstname: String,
    var lastname: String,
    var phone: String,
    var password: String,
    var passwordConfirmation: String,
    var age: Byte,
    var type: UserType
){
    fun validateRequest(){
        if(firstname.isEmptyOrBlank()) throw FieldMustNotBeEmptyException("First Name")
        if(lastname.isEmptyOrBlank()) throw FieldMustNotBeEmptyException("Last Name")
        if(phone.isEmptyOrBlank()) throw PhoneNumberCanNotBeNullException()
        if(password.isEmptyOrBlank()) throw FieldMustNotBeEmptyException("Password")
        if(password != passwordConfirmation) throw PasswordMismatchException()
        if(age < 18 || age > 90) throw InvalidAgeException()
    }
}
