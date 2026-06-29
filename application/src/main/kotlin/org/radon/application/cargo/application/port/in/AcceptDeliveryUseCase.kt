package org.radon.application.cargo.application.port.`in`

import org.radon.application.cargo.presentation.dto.CargoResponse

interface AcceptDeliveryUseCase {
    fun acceptCargo(cargoCode: String): CargoResponse
}