package org.radon.application.user.infrastructure.jpa

import org.radon.application.user.infrastructure.entity.AuthorityEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AuthorityJpaRepository : JpaRepository<AuthorityEntity, Long> {
}