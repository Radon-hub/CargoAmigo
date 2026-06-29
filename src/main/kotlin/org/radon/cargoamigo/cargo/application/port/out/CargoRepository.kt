package org.radon.cargoamigo.cargo.application.port.out

import org.radon.cargoamigo.cargo.domain.Cargo
import org.radon.cargoamigo.cargo.domain.CargoStatus
import org.radon.cargoamigo.cargo.infrastructure.db.CargoEntity
import org.radon.cargoamigo.cargo.presentation.dto.AcceptDeliveryRequest
import org.radon.cargoamigo.cargo.presentation.dto.CargoResponse
import org.radon.cargoamigo.cargo.presentation.dto.RemoveCargoRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.sql.Timestamp

interface CargoRepository {
    fun acceptCargo(request: AcceptDeliveryRequest): Cargo
    fun addNewCargo(request: Cargo): String
    fun removeCargo(request: RemoveCargoRequest): String
    fun updateCargo(request: Cargo): String
    fun getCargos(deadLine: Timestamp?,status: CargoStatus?,price: Double?,ownerPhoneNumber: String?,driverPhoneNumber: String?,pageable: Pageable): Page<CargoResponse>
}