package org.radon.application.user.domain

import org.springframework.security.core.GrantedAuthority

data class Authority(
    val authorityName: String
) : GrantedAuthority {
    override fun getAuthority(): String? = authorityName
}
