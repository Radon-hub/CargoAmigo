package org.radon.cargoservice.presentation.dto

import org.radon.cargoservice.domain.CargoStatus
import java.sql.Timestamp

data class CargoResponse(
    var code: String,
    var description: String,
    var destination: String,
    var source: String,
    var weight: Float,
    var deadLine: Timestamp,
    var status: CargoStatus,
    var price: Float,
    var createdAt: Timestamp,
    var updatedAt: Timestamp
)
