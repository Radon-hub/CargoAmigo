package org.radon.cargoamigo.common

fun String.isEmptyOrBlank(): Boolean {
    return this.isEmpty() || this.isBlank()
}