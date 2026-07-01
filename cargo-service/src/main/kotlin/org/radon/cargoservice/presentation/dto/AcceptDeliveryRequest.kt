package org.radon.cargoservice.presentation.dto

import org.radon.cargoamigo.common.exceptionHandling.CargoCodeCanNotBeNullException
import org.radon.cargoamigo.common.exceptionHandling.PhoneNumberCanNotBeNullException
import org.radon.cargoamigo.common.isEmptyOrBlank

data class AcceptDeliveryRequest(
    var code: String,
    var username: String
){
    fun validateRequest(){
        if(code.isEmptyOrBlank()) throw CargoCodeCanNotBeNullException()
        if(username.isEmptyOrBlank()) throw PhoneNumberCanNotBeNullException()
    }
}