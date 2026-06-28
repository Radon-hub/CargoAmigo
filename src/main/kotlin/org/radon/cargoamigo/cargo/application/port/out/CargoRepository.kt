package org.radon.cargoamigo.cargo.application.port.out

import org.radon.cargoamigo.cargo.domain.Cargo
import org.radon.cargoamigo.cargo.presentation.dto.AcceptDeliveryRequest
import org.radon.cargoamigo.cargo.presentation.dto.RemoveCargoRequest

interface CargoRepository {
    fun acceptCargo(request: AcceptDeliveryRequest): Cargo
    fun addNewCargo(request: Cargo): String
    fun removeCargo(request: RemoveCargoRequest): String
    fun updateCargo(request: Cargo): String
}