package org.radon.cargoservice.domain

object CodeGenerator {
    private const val CHAR_POOL =
        "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"

    fun generateCode(): String {
        return (1..8)
            .map { CHAR_POOL.random() }
            .joinToString("")
    }
}