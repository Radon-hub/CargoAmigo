package org.radon.cargoservice.domain

import java.sql.Timestamp
import java.util.*

data class Cargo(
    var id: UUID?,
    var code: String?,
    var description: String?,
    var destination: String?,
    var source: String?,
    var weight: Float?,
    var deadLine: Timestamp?,
    var status: CargoStatus?,
    var ownerId: UUID?,
    var driverId: UUID?,
    var price: Float?,
    var createdAt: Timestamp?,
    var updatedAt: Timestamp?,
)
