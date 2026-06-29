package org.radon.application.cargo.application.port.`in`

import org.radon.application.cargo.domain.CargoStatus
import org.radon.application.cargo.presentation.dto.CargoResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.sql.Timestamp

interface GetCargosUseCase {
    fun getCargos(deadLine: Timestamp?,status: CargoStatus?,price: Double?,ownerPhoneNumber: String?,driverPhoneNumber: String?,pageable: Pageable): Page<CargoResponse>
}