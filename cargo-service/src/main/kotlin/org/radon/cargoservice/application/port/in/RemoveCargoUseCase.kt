package org.radon.cargoservice.application.port.`in`


interface RemoveCargoUseCase {
    fun removeCargo(cargoCode: String): String
}