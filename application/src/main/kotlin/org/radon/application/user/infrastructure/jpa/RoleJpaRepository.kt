package org.radon.application.user.infrastructure.jpa

import org.radon.application.user.infrastructure.entity.RoleEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface RoleJpaRepository: JpaRepository<RoleEntity, Long> {
    fun getByName(name: String): RoleEntity?
    fun findByName(name: String): Optional<RoleEntity>
}