package org.radon.application.cargo.application.port.`in`


interface RemoveCargoUseCase {
    fun removeCargo(cargoCode: String): String
}