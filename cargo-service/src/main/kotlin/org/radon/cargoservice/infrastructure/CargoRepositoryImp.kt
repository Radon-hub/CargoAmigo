package org.radon.cargoservice.infrastructure

import dto.UserContractDto
import org.radon.cargoservice.application.port.out.CargoRepository
import org.radon.cargoservice.domain.Cargo
import org.radon.cargoservice.domain.CargoStatus
import org.radon.cargoservice.domain.CodeGenerator
import org.radon.cargoservice.domain.toCargo
import org.radon.cargoservice.domain.toCargoEntity
import org.radon.cargoservice.domain.toCargoResponse
import org.radon.cargoservice.infrastructure.db.CargoEntity
import org.radon.cargoservice.infrastructure.db.CargoJpaRepository
import org.radon.cargoservice.infrastructure.db.CargoSpecifications
import org.radon.cargoservice.presentation.dto.AcceptDeliveryRequest
import org.radon.cargoservice.presentation.dto.CargoResponse
import org.radon.cargoservice.presentation.dto.RemoveCargoRequest
import org.radon.cargoamigo.common.UserType
import org.radon.cargoamigo.common.exceptionHandling.CargoCanNotBeAccepted
import org.radon.cargoamigo.common.exceptionHandling.CargoCodeCanNotBeNullException
import org.radon.cargoamigo.common.exceptionHandling.CargoNotBelongsToUserException
import org.radon.cargoamigo.common.exceptionHandling.CargoNotFoundException
import org.radon.cargoamigo.common.exceptionHandling.CargoStatusCanNotBeChanged
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Repository
import java.sql.Timestamp

@Repository
open class CargoRepositoryImp(
    private val cargoRepository: CargoJpaRepository,
): CargoRepository {


    override fun acceptCargo(request: AcceptDeliveryRequest,user:UserContractDto): Cargo {

        val cargo = cargoRepository.findCargoByCode(request.code).orElseThrow { CargoNotFoundException() }

        if(cargo.status != CargoStatus.ACTIVE){
            throw CargoCanNotBeAccepted()
        }
        if(cargo.ownerId == user){
            throw CargoCanNotBeAccepted()
        }
        if(user.type == UserType.EMPLOYER){
            throw CargoCanNotBeAccepted()
        }

        cargo.driverId = user.id
        cargo.status = CargoStatus.ACCEPTED
        cargo.updatedAt = Timestamp(System.currentTimeMillis())

        return cargo.toCargo()

    }

    override fun addNewCargo(request: Cargo, user:UserContractDto): String {

        val code = CodeGenerator.generateCode()

        if(cargoRepository.findCargoByCode(code).isPresent){
            return addNewCargo(request,user)
        }

        val cargo = request.toCargoEntity()

        cargo.code = code

        cargo.ownerId = user.id

        cargoRepository.saveAndFlush(cargo)

        return cargo.code!!

    }

    override fun removeCargo(request: RemoveCargoRequest,user:UserContractDto): String {

        val cargo = cargoRepository.findCargoByCode(request.code).orElseThrow { CargoNotFoundException() }

        if(cargo.ownerId != user.id){
            throw CargoNotBelongsToUserException()
        }

        if(cargo.status != CargoStatus.ACTIVE){
            throw CargoStatusCanNotBeChanged()
        }

        cargo.status = CargoStatus.REJECTED

        return request.code

    }

    override fun updateCargo(request: Cargo,user:UserContractDto): String {

        val cargo = cargoRepository.findCargoByCode(request.code ?: throw CargoCodeCanNotBeNullException()).orElseThrow { CargoNotFoundException() }

        if(cargo.ownerId != user.id){
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
        ownerPhone: String?,
        driverPhone: String?,
        pageable: Pageable,
        owner:UserContractDto?,
        driver:UserContractDto?
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

        owner?.let {
            spec = spec.and(CargoSpecifications.ownerEquals(it.id!!))
        }
        driver?.let {
            spec = spec.and(CargoSpecifications.driverEquals(it.id!!))
        }

        return cargoRepository.findAll(spec, pageable).map { it.toCargo().toCargoResponse() }

    }
}