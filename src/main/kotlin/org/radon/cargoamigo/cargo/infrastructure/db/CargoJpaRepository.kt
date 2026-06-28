package org.radon.cargoamigo.cargo.infrastructure.db

import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import java.util.UUID

interface CargoJpaRepository: JpaRepository<CargoEntity, UUID> {
    fun findCargoByCode(code: String): Optional<CargoEntity>
}