package org.radon.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.persistence.autoconfigure.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication(scanBasePackages = [
    "org.radon.userservice",
    "org.radon.cargoservice",
    "org.radon.cargoamigo",
    "org.radon.application",
])
@EnableJpaRepositories(
    basePackages = [
        "org.radon.userservice.infrastructure.jpa",
        "org.radon.cargoservice.infrastructure.db"
    ]
)
@EntityScan(
    basePackages = [
        "org.radon.userservice.infrastructure.entity",
        "org.radon.cargoservice.infrastructure.db"
    ]
)
class CargoAmigoApplication

fun main(args: Array<String>) {
    runApplication<CargoAmigoApplication>(*args)
}
