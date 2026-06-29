package org.radon.cargoamigo.cargo.infrastructure

import org.radon.cargoamigo.auth.infrastructure.jpa.UserJpaRepository
import org.radon.cargoamigo.cargo.application.port.out.CargoRepository
import org.radon.cargoamigo.cargo.domain.Cargo
import org.radon.cargoamigo.cargo.domain.CargoStatus
import org.radon.cargoamigo.cargo.domain.CodeGenerator
import org.radon.cargoamigo.cargo.domain.toCargo
import org.radon.cargoamigo.cargo.domain.toCargoEntity
import org.radon.cargoamigo.cargo.domain.toCargoResponse
import org.radon.cargoamigo.cargo.infrastructure.db.CargoEntity
import org.radon.cargoamigo.cargo.infrastructure.db.CargoJpaRepository
import org.radon.cargoamigo.cargo.infrastructure.db.CargoSpecifications
import org.radon.cargoamigo.cargo.presentation.dto.AcceptDeliveryRequest
import org.radon.cargoamigo.cargo.presentation.dto.CargoResponse
import org.radon.cargoamigo.cargo.presentation.dto.RemoveCargoRequest
import org.radon.cargoamigo.common.UserType
import org.radon.cargoamigo.common.exceptionHandling.CargoCanNotBeAccepted
import org.radon.cargoamigo.common.exceptionHandling.CargoCodeCanNotBeNullException
import org.radon.cargoamigo.common.exceptionHandling.CargoNotBelongsToUserException
import org.radon.cargoamigo.common.exceptionHandling.CargoNotFoundException
import org.radon.cargoamigo.common.exceptionHandling.CargoStatusCanNotBeChanged
import org.radon.cargoamigo.common.exceptionHandling.FieldMustNotBeEmptyException
import org.radon.cargoamigo.common.exceptionHandling.UserNotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Repository
import java.sql.Timestamp

@Repository
class CargoRepositoryImp(
    private val cargoRepository: CargoJpaRepository,
    private val userRepository: UserJpaRepository
): CargoRepository {


    override fun acceptCargo(request: AcceptDeliveryRequest): Cargo {

        val user = userRepository.findUserByPhoneNumber(request.username).orElseThrow { UserNotFoundException() }

        val cargo = cargoRepository.findCargoByCode(request.code).orElseThrow { CargoNotFoundException() }

        if(cargo.status != CargoStatus.ACTIVE){
            throw CargoCanNotBeAccepted()
        }
        if(cargo.owner == user){
            throw CargoCanNotBeAccepted()
        }
        if(user.type == UserType.EMPLOYER){
            throw CargoCanNotBeAccepted()
        }

        cargo.driver = user
        cargo.status = CargoStatus.ACCEPTED
        cargo.updatedAt = Timestamp(System.currentTimeMillis())

        return cargo.toCargo()

    }

    override fun addNewCargo(request: Cargo): String {

        val code = CodeGenerator.generateCode()

        if(cargoRepository.findCargoByCode(code).isPresent){
            return addNewCargo(request)
        }

        val cargo = request.toCargoEntity()

        cargo.code = code

        val owner = userRepository.findUserByPhoneNumber(request.owner?.username ?: throw FieldMustNotBeEmptyException("User phone number")).orElseThrow { UserNotFoundException() }

        cargo.owner = owner

        cargoRepository.saveAndFlush(cargo)

        return cargo.code!!

    }

    override fun removeCargo(request: RemoveCargoRequest): String {

        val cargo = cargoRepository.findCargoByCode(request.code).orElseThrow { CargoNotFoundException() }

        if(cargo.owner?.phoneNumber != request.username){
            throw CargoNotBelongsToUserException()
        }

        if(cargo.status != CargoStatus.ACTIVE){
            throw CargoStatusCanNotBeChanged()
        }

        cargo.status = CargoStatus.REJECTED

        return request.code

    }

    override fun updateCargo(request: Cargo): String {

        val cargo = cargoRepository.findCargoByCode(request.code ?: throw CargoCodeCanNotBeNullException()).orElseThrow { CargoNotFoundException() }

        if(cargo.owner?.phoneNumber != request.owner?.username){
            throw CargoNotBelongsToUserException()
        }

        cargo.updatedAt = Timestamp(System.currentTimeMillis())

        if(cargo.status != CargoStatus.ACTIVE){
            throw CargoStatusCanNotBeChanged()
        }

        cargo.price = request.price
        cargo.deadLine = request.deadLine
        cargo.weight = request.weight
        cargo.description = request.description
        cargo.source = request.source
        cargo.destination = request.destination

        return cargo.code!!
    }

    override fun getCargos(
        deadLine: Timestamp?,
        status: CargoStatus?,
        price: Double?,
        ownerPhoneNumber: String?,
        driverPhoneNumber: String?,
        pageable: Pageable
    ): Page<CargoResponse> {

        var spec: Specification<CargoEntity> = Specification.allOf()

        deadLine?.let {
            spec = spec.and(CargoSpecifications.deadLineEquals(it))
        }
        status?.let {
            spec = spec.and(CargoSpecifications.statusEquals(it))
        }
        price?.let {
            spec = spec.and(CargoSpecifications.priceBiggerThan(it))
        }
        ownerPhoneNumber?.let {
            spec = spec.and(CargoSpecifications.ownerEquals(it))
        }
        driverPhoneNumber?.let {
            spec = spec.and(CargoSpecifications.driverEquals(it))
        }

        return cargoRepository.findAll(spec, pageable).map { it.toCargo().toCargoResponse() }

    }
}