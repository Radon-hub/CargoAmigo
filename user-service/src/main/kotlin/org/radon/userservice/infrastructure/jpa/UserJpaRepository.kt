package org.radon.userservice.infrastructure.jpa

import org.radon.userservice.infrastructure.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import java.util.UUID

interface UserJpaRepository : JpaRepository<UserEntity, UUID> {
    fun findUserByPhoneNumber(phoneNumber: String): Optional<UserEntity>
    fun existsByPhoneNumber(phoneNumber: String): Boolean
    fun findUserById(id: UUID): Optional<UserEntity>
}