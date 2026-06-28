package org.radon.cargoamigo.cargo.domain

import org.radon.cargoamigo.auth.domain.User
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
    var owner: User?,
    var driver: User?,
    var price: Float?,
    var createdAt: Timestamp?,
    var updatedAt: Timestamp?,
)
