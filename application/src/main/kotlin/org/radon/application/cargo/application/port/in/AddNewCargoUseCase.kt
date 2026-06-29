package org.radon.application.cargo.application.port.`in`

import org.radon.application.cargo.presentation.dto.CargoRequest

interface AddNewCargoUseCase {
    fun addNewCargo(request: CargoRequest): String
}