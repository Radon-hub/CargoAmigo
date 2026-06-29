package org.radon.application.cargo.application.service

import jakarta.transaction.Transactional
import org.radon.application.cargo.application.port.`in`.AcceptDeliveryUseCase
import org.radon.application.cargo.application.port.`in`.AddNewCargoUseCase
import org.radon.application.cargo.application.port.`in`.GetCargosUseCase
import org.radon.application.cargo.application.port.`in`.RemoveCargoUseCase
import org.radon.application.cargo.application.port.`in`.UpdateCargoUseCase
import org.radon.application.cargo.application.port.out.CargoRepository
import org.radon.application.cargo.domain.CargoStatus
import org.radon.application.cargo.domain.toCargo
import org.radon.application.cargo.domain.toCargoResponse
import org.radon.application.cargo.presentation.dto.AcceptDeliveryRequest
import org.radon.application.cargo.presentation.dto.CargoRequest
import org.radon.application.cargo.presentation.dto.CargoResponse
import org.radon.application.cargo.presentation.dto.RemoveCargoRequest
import org.radon.application.user.domain.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.sql.Timestamp

@Service
class CargoService(
    private val cargoRepository: CargoRepository,
): AcceptDeliveryUseCase, AddNewCargoUseCase, RemoveCargoUseCase, UpdateCargoUseCase, GetCargosUseCase {

    fun getUserName(): String {
        return SecurityContextHolder
            .getContext()
            .authentication?.name ?: "Unknown"
    }

    fun getUser(): User {
        return SecurityContextHolder
            .getContext()
            .authentication?.principal as User
    }

    @Transactional
    override fun acceptCargo(cargoCode: String): CargoResponse {
        return cargoRepository.acceptCargo(AcceptDeliveryRequest(
            code = cargoCode,
            username = getUserName(),
        )).toCargoResponse()
    }

    @Transactional
    override fun addNewCargo(request: CargoRequest): String {
        return cargoRepository.addNewCargo(
            request.toCargo().copy(
                owner = getUser()
            )
        )
    }

    @Transactional
    override fun removeCargo(cargoCode: String): String {
        return cargoRepository.removeCargo(
            RemoveCargoRequest(
                code = cargoCode,
                username = getUserName(),
            )
        )
    }

    @Transactional
    override fun updateCargo(request: CargoRequest): String {
        return cargoRepository.updateCargo(
            request.toCargo().copy(
                owner = getUser()
            )
        )
    }

    override fun getCargos(
        deadLine: Timestamp?,
        status: CargoStatus?,
        price: Double?,
        ownerPhoneNumber: String?,
        driverPhoneNumber: String?,
        pageable: Pageable
    ): Page<CargoResponse> {
        return cargoRepository.getCargos(deadLine, status, price, ownerPhoneNumber, driverPhoneNumber, pageable)
    }

}