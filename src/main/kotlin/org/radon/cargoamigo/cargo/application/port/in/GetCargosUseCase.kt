package org.radon.cargoamigo.cargo.application.port.`in`

import org.radon.cargoamigo.cargo.domain.CargoStatus
import org.radon.cargoamigo.cargo.infrastructure.db.CargoEntity
import org.radon.cargoamigo.cargo.presentation.dto.CargoResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.sql.Timestamp

interface GetCargosUseCase {
    fun getCargos(deadLine: Timestamp?,status: CargoStatus?,price: Double?,ownerPhoneNumber: String?,driverPhoneNumber: String?,pageable: Pageable): Page<CargoResponse>
}