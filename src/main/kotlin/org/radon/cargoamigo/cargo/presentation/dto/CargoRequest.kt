package org.radon.cargoamigo.cargo.presentation.dto

import org.radon.cargoamigo.cargo.domain.CargoStatus
import java.sql.Timestamp

data class CargoRequest(
    var code: String? = null,
    var description: String,
    var destination: String,
    var source: String,
    var weight: Float,
    var deadLine: Timestamp,
    var price: Float
)
