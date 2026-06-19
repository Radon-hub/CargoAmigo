package org.radon.cargoamigo.common

data class Response<T>(
    val data: T,
    val error: ErrorResponse? = null
)
