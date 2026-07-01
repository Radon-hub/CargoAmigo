package org.radon.cargoservice.application.port.out

import dto.UserContractDto
import org.radon.cargoservice.domain.Cargo
import org.radon.cargoservice.domain.CargoStatus
import org.radon.cargoservice.presentation.dto.AcceptDeliveryRequest
import org.radon.cargoservice.presentation.dto.CargoResponse
import org.radon.cargoservice.presentation.dto.RemoveCargoRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.sql.Timestamp
import java.util.UUID

interface CargoRepository {
    fun acceptCargo(request: AcceptDeliveryRequest,user:UserContractDto): Cargo
    fun addNewCargo(request: Cargo,user:UserContractDto): String
    fun removeCargo(request: RemoveCargoRequest,user:UserContractDto): String
    fun updateCargo(request: Cargo,user:UserContractDto): String
    fun getCargos(deadLine: Timestamp?,status: CargoStatus?,price: Double?,ownerPhone: String?,driverPhone: String?,pageable: Pageable, owner:UserContractDto?, driver:UserContractDto?): Page<CargoResponse>
}