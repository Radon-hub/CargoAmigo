package org.radon.application.user.common

import org.radon.application.user.infrastructure.entity.UserEntity
import org.radon.application.user.domain.User

fun UserEntity.toUser(): User = User(
    id = this.id,
    firstName = this.firstName,
    lastName = this.lastName,
    age = this.age,
    phoneNumber = this.phoneNumber,
    type = this.type,
    enabled = this.enabled,
)