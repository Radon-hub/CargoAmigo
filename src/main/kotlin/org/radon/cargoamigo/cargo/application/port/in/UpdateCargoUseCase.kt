package org.radon.cargoamigo.cargo.application.port.`in`

import org.radon.cargoamigo.cargo.presentation.dto.CargoRequest

interface UpdateCargoUseCase {
    fun updateCargo(request: CargoRequest): String
}