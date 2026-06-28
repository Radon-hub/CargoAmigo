package org.radon.cargoamigo.cargo.application.port.`in`

import org.radon.cargoamigo.cargo.presentation.dto.AcceptDeliveryRequest
import org.radon.cargoamigo.cargo.presentation.dto.CargoResponse

interface AcceptDeliveryUseCase {
    fun acceptCargo(cargoCode: String): CargoResponse
}