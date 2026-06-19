package org.radon.cargoamigo.auth.infrastructure.jpa

import org.radon.cargoamigo.auth.infrastructure.entity.AuthorityEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AuthorityJpaRepository : JpaRepository<AuthorityEntity, Long> {
}