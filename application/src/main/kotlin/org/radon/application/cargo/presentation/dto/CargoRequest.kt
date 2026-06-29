package org.radon.application.cargo.presentation.dto

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
