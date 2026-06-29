package org.radon.application.user.domain


data class Role(
    val name: String? = null,
    val authorities: Set<Authority>
)
