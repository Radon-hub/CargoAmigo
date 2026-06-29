package org.radon.application.cargo.infrastructure.db

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import lombok.AllArgsConstructor
import org.radon.application.cargo.domain.CargoStatus
import org.radon.application.user.infrastructure.entity.UserEntity
import java.sql.Timestamp
import java.util.UUID

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
    @ManyToOne(cascade = [CascadeType.ALL])
    var owner: UserEntity? = null
    @ManyToOne(cascade = [CascadeType.ALL])
    var driver: UserEntity? = null
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