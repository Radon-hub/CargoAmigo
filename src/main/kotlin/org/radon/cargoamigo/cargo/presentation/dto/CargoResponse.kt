package org.radon.cargoamigo.cargo.presentation.dto

import org.radon.cargoamigo.cargo.domain.CargoStatus
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
