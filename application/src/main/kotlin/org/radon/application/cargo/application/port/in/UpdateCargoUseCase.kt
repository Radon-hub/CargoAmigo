package org.radon.application.cargo.application.port.`in`

import org.radon.application.cargo.presentation.dto.CargoRequest

interface UpdateCargoUseCase {
    fun updateCargo(request: CargoRequest): String
}