package org.radon.cargoservice.application.service

import jakarta.transaction.Transactional
import org.radon.cargoamigo.common.exceptionHandling.UserNotFoundException
import org.radon.cargoservice.application.port.`in`.*
import org.radon.cargoservice.application.port.out.CargoRepository
import org.radon.cargoservice.domain.CargoStatus
import org.radon.cargoservice.domain.toCargo
import org.radon.cargoservice.domain.toCargoResponse
import org.radon.cargoservice.presentation.dto.AcceptDeliveryRequest
import org.radon.cargoservice.presentation.dto.CargoRequest
import org.radon.cargoservice.presentation.dto.CargoResponse
import org.radon.cargoservice.presentation.dto.RemoveCargoRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import port.GetContextUserUseCase
import port.GetUserWithPhoneNumberUseCase
import java.sql.Timestamp

@Service
open class CargoService(
    private val cargoRepository: CargoRepository,
    private val getUserWithPhoneNumberUseCase: GetUserWithPhoneNumberUseCase,
    private val getContextUserUseCase: GetContextUserUseCase
): AcceptDeliveryUseCase, AddNewCargoUseCase, RemoveCargoUseCase, UpdateCargoUseCase, GetCargosUseCase {

    fun getUserName() = getContextUserUseCase.getUser().phoneNumber ?: "Unknown"
    fun getUser() = getContextUserUseCase.getUser()

    @Transactional
    override fun acceptCargo(cargoCode: String): CargoResponse {
        return cargoRepository.acceptCargo(AcceptDeliveryRequest(
            code = cargoCode,
            username = getUserName(),
        ),
            getUserWithPhoneNumberUseCase.getWithPhoneNumber(getUserName())).toCargoResponse()
    }

    @Transactional
    override fun addNewCargo(request: CargoRequest): String {
        return cargoRepository.addNewCargo(
            request.toCargo(),
            getUserWithPhoneNumberUseCase.getWithPhoneNumber(getUser().phoneNumber ?: throw UserNotFoundException())
        )
    }

    @Transactional
    override fun removeCargo(cargoCode: String): String {
        return cargoRepository.removeCargo(
            RemoveCargoRequest(
                code = cargoCode,
                username = getUserName(),
            ),
            getUserWithPhoneNumberUseCase.getWithPhoneNumber(getUserName())
        )
    }

    @Transactional
    override fun updateCargo(request: CargoRequest): String {
        return cargoRepository.updateCargo(
            request.toCargo(),
            getUserWithPhoneNumberUseCase.getWithPhoneNumber(getUser().phoneNumber ?: throw UserNotFoundException())
        )
    }
    @Transactional
    override fun getCargos(
        deadLine: Timestamp?,
        status: CargoStatus?,
        price: Double?,
        ownerPhoneNumber: String?,
        driverPhoneNumber: String?,
        pageable: Pageable
    ): Page<CargoResponse> {
        return cargoRepository.getCargos(deadLine, status, price, ownerPhoneNumber, driverPhoneNumber, pageable,
            owner = ownerPhoneNumber?.let { getUserWithPhoneNumberUseCase.getWithPhoneNumber(it) },
            driver = driverPhoneNumber?.let { getUserWithPhoneNumberUseCase.getWithPhoneNumber(it) }
        )
    }

}