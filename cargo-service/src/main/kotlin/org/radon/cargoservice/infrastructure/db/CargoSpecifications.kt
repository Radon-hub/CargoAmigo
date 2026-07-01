package org.radon.cargoservice.infrastructure.db

import org.radon.cargoservice.domain.CargoStatus
import org.springframework.data.jpa.domain.Specification
import java.sql.Timestamp
import java.util.UUID

object CargoSpecifications {

    fun deadLineEquals(time: Timestamp): Specification<CargoEntity> {
        return Specification { root,_,cb ->
            cb.greaterThanOrEqualTo(root.get("deadLine"), time)
        }
    }

    fun statusEquals(status: CargoStatus): Specification<CargoEntity> {
        return Specification { root,_,cb ->
            cb.equal(root.get<String>("status"), status)
        }
    }

    fun priceBiggerThan(price: Double): Specification<CargoEntity> {
        return Specification { root,_,cb ->
            cb.greaterThanOrEqualTo(root.get<Double>("price"), price)
        }
    }

    fun driverEquals(driverId: UUID): Specification<CargoEntity> {
        return Specification { root,_,cb ->
            cb.equal(root.get<UUID>("driverId"), driverId)
        }
    }

    fun ownerEquals(ownerId: UUID): Specification<CargoEntity> {
        return Specification { root,_,cb ->
            cb.equal(root.get<UUID>("ownerId"), ownerId)
        }
    }

}