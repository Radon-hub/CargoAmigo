package org.radon.cargoamigo.auth.domain

import jakarta.persistence.Column
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import org.radon.cargoamigo.common.UserRole
import org.radon.cargoamigo.common.UserType
import java.util.UUID

data class User(
    val id: UUID,
    val firstName: String,
    val lastName: String,
    val age: Byte,
    val phoneNumber: String,
    val password: String,
    val role: UserRole,
    val type: UserType,
    val enabled: Boolean = true,
)
