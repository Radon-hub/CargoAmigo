package org.radon.userservice.domain


data class Role(
    val name: String? = null,
    val authorities: Set<Authority>
)
