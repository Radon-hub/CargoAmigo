package org.radon.cargoamigo.auth.infrastructure

import org.radon.cargoamigo.auth.infrastructure.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserJpaRepository : JpaRepository<UserEntity, UUID> {
    fun findUserByPhoneNumber(phoneNumber: String): UserEntity?
    fun existsByPhoneNumber(phoneNumber: String): Boolean
    fun isUserEnabledByPhoneNumber(phoneNumber: String): Boolean
}