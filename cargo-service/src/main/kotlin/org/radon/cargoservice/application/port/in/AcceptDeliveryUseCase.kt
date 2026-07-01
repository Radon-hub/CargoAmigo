package org.radon.cargoservice.application.port.`in`

import org.radon.cargoservice.presentation.dto.CargoResponse

interface AcceptDeliveryUseCase {
    fun acceptCargo(cargoCode: String): CargoResponse
}