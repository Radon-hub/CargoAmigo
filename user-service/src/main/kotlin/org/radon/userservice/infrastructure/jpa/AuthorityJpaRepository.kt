package org.radon.userservice.infrastructure.jpa

import org.radon.userservice.infrastructure.entity.AuthorityEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AuthorityJpaRepository : JpaRepository<AuthorityEntity, Long> {
}