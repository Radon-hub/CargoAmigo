package org.radon.application.cargo.domain

import org.radon.application.user.common.toUser
import org.radon.application.cargo.infrastructure.db.CargoEntity
import org.radon.application.cargo.presentation.dto.CargoRequest
import org.radon.application.cargo.presentation.dto.CargoResponse
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
    owner = this.owner?.toUser(),
    driver = this.driver?.toUser(),
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
    owner = null,
    driver = null,
    createdAt = Timestamp(System.currentTimeMillis()),
    updatedAt = Timestamp(System.currentTimeMillis()),
)