package org.radon.cargoamigo.cargo.presentation

import org.radon.cargoamigo.cargo.application.port.`in`.*
import org.radon.cargoamigo.cargo.domain.CargoStatus
import org.radon.cargoamigo.cargo.presentation.dto.CargoRequest
import org.radon.cargoamigo.cargo.presentation.dto.CargoResponse
import org.radon.cargoamigo.common.Response
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.sql.Timestamp
import java.time.LocalDate

@RestController
@RequestMapping("api/v1/cargo")
class CargoController(
    private val acceptDeliveryUseCase: AcceptDeliveryUseCase,
    private val addNewCargoUseCase: AddNewCargoUseCase,
    private val removeCargoUseCase: RemoveCargoUseCase,
    private val updateCargoUseCase: UpdateCargoUseCase,
    private val getCargosUseCase: GetCargosUseCase
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

    @GetMapping("{status}")
    fun searchCargos(
        @PathVariable("status") status: CargoStatus,
        @RequestParam("price") price: Double?,
        @RequestParam("owner") owner: String?,
        @RequestParam("driver") driver: String?,
        @RequestParam("deadline")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        deadline: LocalDate?,
        @RequestParam(value = "page", defaultValue = "0") page: Int?,
        @RequestParam(value = "size", defaultValue = "25") size: Int?,
    ): ResponseEntity<Response<Page<CargoResponse>>> {
        return ResponseEntity.ok(Response(getCargosUseCase.getCargos(
            deadLine = Timestamp.valueOf(deadline?.atStartOfDay()),
            status = status,
            price = price,
            ownerPhoneNumber = owner,
            driverPhoneNumber = driver,
            pageable = PageRequest.of(page ?: 0, size ?: 25,)
        )))
    }
}