package org.radon.cargoservice.infrastructure.db

import jakarta.persistence.*
import org.radon.cargoservice.domain.CargoStatus
import java.sql.Timestamp
import java.util.*

@Entity
@Table(name = "cargos")
class CargoEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null
    @Column(nullable = false,unique = true)
    var code: String? = null
    @Column(nullable = false)
    var description: String? = null
    @Column(nullable = false)
    var destination: String? = null
    @Column(nullable = false)
    var source: String? = null
    @Column(nullable = false)
    var weight: Float? = null
    @Column(nullable = false)
    var deadLine: Timestamp? = null
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var status: CargoStatus = CargoStatus.ACTIVE
    @Column(nullable = false)
    var ownerId: UUID? = null
    @Column(nullable = true)
    var driverId: UUID? = null
    @Column(nullable = false)
    var price: Float? = null
    @Column(nullable = false)
    var createdAt: Timestamp = Timestamp(System.currentTimeMillis())
    @Column(nullable = false)
    var updatedAt: Timestamp =  Timestamp(System.currentTimeMillis())

    constructor()

    constructor(
        description: String?,
        destination: String?,
        source: String?,
        weight: Float?,
        deadLine: Timestamp?,
        price: Float?
    ) {
        this.description = description
        this.destination = destination
        this.source = source
        this.weight = weight
        this.deadLine = deadLine
        this.price = price
    }
}