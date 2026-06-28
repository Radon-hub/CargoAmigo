package org.radon.cargoamigo.cargo.presentation

import jakarta.websocket.server.PathParam
import org.radon.cargoamigo.cargo.application.port.`in`.AcceptDeliveryUseCase
import org.radon.cargoamigo.cargo.application.port.`in`.AddNewCargoUseCase
import org.radon.cargoamigo.cargo.application.port.`in`.RemoveCargoUseCase
import org.radon.cargoamigo.cargo.application.port.`in`.UpdateCargoUseCase
import org.radon.cargoamigo.cargo.presentation.dto.AcceptDeliveryRequest
import org.radon.cargoamigo.cargo.presentation.dto.CargoRequest
import org.radon.cargoamigo.cargo.presentation.dto.RemoveCargoRequest
import org.radon.cargoamigo.common.Response
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/cargo")
class CargoController(
    private val acceptDeliveryUseCase: AcceptDeliveryUseCase,
    private val addNewCargoUseCase: AddNewCargoUseCase,
    private val removeCargoUseCase: RemoveCargoUseCase,
    private val updateCargoUseCase: UpdateCargoUseCase,
) {

    @PostMapping("{cargoCode}")
    fun acceptCargo(
        @PathVariable("cargoCode") cargoCode: String
    ): ResponseEntity<Response<String>> {
        return ResponseEntity.ok(Response(acceptDeliveryUseCase.acceptCargo(cargoCode).code))
    }

    @PostMapping()
    fun addNewCargo(@RequestBody request: CargoRequest): ResponseEntity<Response<String>> {
        return ResponseEntity.ok(Response(addNewCargoUseCase.addNewCargo(request)))
    }

    @DeleteMapping("{cargoCode}")
    fun removeCargo(
        @PathVariable("cargoCode") cargoCode: String
    ): ResponseEntity<Response<String>> {
        return ResponseEntity.ok(Response(removeCargoUseCase.removeCargo(cargoCode)))
    }

    @PutMapping
    fun updateCargo(@RequestBody request: CargoRequest): ResponseEntity<Response<String>> {
        return ResponseEntity.ok(Response(updateCargoUseCase.updateCargo(request)))
    }
}