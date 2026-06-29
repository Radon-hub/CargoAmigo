package org.radon.application.cargo.infrastructure.db

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import java.util.Optional
import java.util.UUID

interface CargoJpaRepository: JpaRepository<CargoEntity, UUID>, JpaSpecificationExecutor<CargoEntity> {
    fun findCargoByCode(code: String): Optional<CargoEntity>
}