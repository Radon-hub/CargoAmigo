package org.radon.cargoservice.domain

import org.radon.cargoservice.infrastructure.db.CargoEntity
import org.radon.cargoservice.presentation.dto.CargoRequest
import org.radon.cargoservice.presentation.dto.CargoResponse
import java.sql.Timestamp


fun CargoEntity.toCargo(): Cargo = Cargo(
    id = this.id,
    code = this.code,
    description = this.description,
    destination = this.destination,
    source = this.source,
    weight = this.weight,
    deadLine = this.deadLine,
    status = this.status,
    ownerId = this.ownerId,
    driverId = this.driverId,
    price = this.price,
    createdAt = this.createdAt,
    updatedAt = this.updatedAt,
)

fun Cargo.toCargoEntity(): CargoEntity = CargoEntity(
    description = this.description,
    destination = this.destination,
    source = this.source,
    weight = this.weight,
    deadLine = this.deadLine,
    price = this.price
)

fun Cargo.toCargoResponse(): CargoResponse = CargoResponse(
    code = this.code ?: "",
    description = this.description ?: "",
    destination = this.destination ?: "",
    source = this.source ?: "",
    weight = this.weight ?: 0f,
    deadLine = this.deadLine ?: Timestamp(System.currentTimeMillis()),
    status = this.status ?: CargoStatus.ACTIVE,
    price = this.price ?: 0f,
    createdAt = this.createdAt ?: Timestamp(System.currentTimeMillis()),
    updatedAt = this.updatedAt ?: Timestamp(System.currentTimeMillis()),
)

fun CargoRequest.toCargo(): Cargo = Cargo(
    id = null,
    code = this.code,
    description = this.description,
    destination = this.destination,
    source = this.source,
    weight = this.weight,
    deadLine = this.deadLine,
    status = CargoStatus.ACTIVE,
    price = this.price,
    ownerId = null,
    driverId = null,
    createdAt = Timestamp(System.currentTimeMillis()),
    updatedAt = Timestamp(System.currentTimeMillis()),
)