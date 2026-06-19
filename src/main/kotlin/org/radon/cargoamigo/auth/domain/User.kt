package org.radon.cargoamigo.auth.domain

import org.radon.cargoamigo.common.UserType
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.UUID

data class User(
    val id: UUID? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val age: Byte? = null,
    val phoneNumber: String? = null,
    val passwordHash: String? = null,
    val role: Role? = null,
    val type: UserType? = null,
    val enabled: Boolean = true,
): UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority> = role?.authorities ?: emptyList()

    override fun getPassword(): String? = passwordHash

    override fun getUsername(): String = phoneNumber ?: ""
}
