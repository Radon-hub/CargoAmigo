package org.radon.cargoservice.application.port.`in`

import org.radon.cargoservice.presentation.dto.CargoRequest


interface AddNewCargoUseCase {
    fun addNewCargo(request: CargoRequest): String
}