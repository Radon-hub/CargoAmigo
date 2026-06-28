package org.radon.cargoamigo.auth.domain

import org.radon.cargoamigo.auth.infrastructure.entity.UserEntity

fun UserEntity.toUser(): User = User(
    id = this.id,
    firstName = this.firstName,
    lastName = this.lastName,
    age = this.age,
    phoneNumber = this.phoneNumber,
    type = this.type,
    enabled = this.enabled,
)