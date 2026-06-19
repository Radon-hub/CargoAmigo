package org.radon.cargoamigo.auth.domain

data class Role(
    val name: String? = null,
    val authorities: Set<Authority>
)
