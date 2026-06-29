package org.radon.cargoamigo.common

import java.time.LocalDateTime


data class ErrorResponse(
    val timestamp: LocalDateTime,
    val status: Int,
    val error: String,
    val path: String,
    val message: String
){
    constructor(
        status: Int,
        error: String,
        path: String,
        message: String
    ) : this(
        timestamp = LocalDateTime.now(),
        status = status,
        error = error,
        path = path,
        message = message
    )
}
