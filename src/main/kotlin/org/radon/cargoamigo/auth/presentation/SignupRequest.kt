package org.radon.cargoamigo.auth.presentation

import org.radon.cargoamigo.common.Gender
import org.radon.cargoamigo.common.UserType

data class SignupRequest(
    val userName: String,
    val password: String,
    val passwordConfirmation: String,
    val age: Byte,
    val gender: Gender,
    val type: UserType
)
