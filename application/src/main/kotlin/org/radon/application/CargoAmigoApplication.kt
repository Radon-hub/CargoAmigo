package org.radon.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CargoAmigoApplication

fun main(args: Array<String>) {
    runApplication<CargoAmigoApplication>(*args)
}
