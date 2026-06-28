package org.radon.cargoamigo.cargo.application.port.`in`

import org.radon.cargoamigo.cargo.presentation.dto.CargoRequest

interface AddNewCargoUseCase {
    fun addNewCargo(request: CargoRequest): String
}