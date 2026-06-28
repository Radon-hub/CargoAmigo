package org.radon.cargoamigo.cargo.application.port.`in`

import org.radon.cargoamigo.cargo.presentation.dto.RemoveCargoRequest

interface RemoveCargoUseCase {
    fun removeCargo(cargoCode: String): String
}