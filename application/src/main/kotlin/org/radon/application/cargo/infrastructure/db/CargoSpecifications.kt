package org.radon.application.cargo.infrastructure.db

import org.radon.application.cargo.domain.CargoStatus
import org.radon.application.user.infrastructure.entity.UserEntity
import org.springframework.data.jpa.domain.Specification
import java.sql.Timestamp

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

    fun driverEquals(driverPhoneNumber: String): Specification<CargoEntity> {
        return Specification { root,_,cb ->
            cb.equal(root.get<UserEntity>("driver").get<String>("phoneNumber"), driverPhoneNumber)
        }
    }

    fun ownerEquals(ownerPhoneNumber: String): Specification<CargoEntity> {
        return Specification { root,_,cb ->
            cb.equal(root.get<UserEntity>("owner").get<String>("phoneNumber"), ownerPhoneNumber)
        }
    }

}