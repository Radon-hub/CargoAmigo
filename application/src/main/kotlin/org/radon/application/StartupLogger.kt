package org.radon.application

import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class StartupLogger(
    @Value("\${spring.datasource.url}")
    private val url: String
) {
    @PostConstruct
    fun log() {
        println("Datasource: $url")
    }
}