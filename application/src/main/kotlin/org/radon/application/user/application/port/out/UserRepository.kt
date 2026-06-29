package org.radon.application.user.application.port.out

import org.radon.application.user.domain.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService

interface UserRepository : UserDetailsService {
    fun createUser(user: User?): User?
    override fun loadUserByUsername(username: String): UserDetails
}