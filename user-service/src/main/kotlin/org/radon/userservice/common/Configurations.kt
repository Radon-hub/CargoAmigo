package org.radon.userservice.common

import org.radon.userservice.infrastructure.entity.AuthorityEntity
import org.radon.userservice.infrastructure.entity.RoleEntity
import org.radon.userservice.infrastructure.jpa.AuthorityJpaRepository
import org.radon.userservice.infrastructure.jpa.RoleJpaRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
open class Configurations {

    @Bean
    open fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    open fun cmdRunner(authRepo: AuthorityJpaRepository, roleJpaRepo: RoleJpaRepository): CommandLineRunner {
        return CommandLineRunner { args ->
            if (roleJpaRepo.findByName("ADMIN").isEmpty) {
                val read = authRepo.save(AuthorityEntity(authority = "READ"))
                val add = authRepo.save(AuthorityEntity(authority = "ADD"))
                val delete = authRepo.save(AuthorityEntity(authority = "DELETE"))
                val update = authRepo.save(AuthorityEntity(authority = "UPDATE"))
                roleJpaRepo.save(RoleEntity(name = "ADMIN", authorities = mutableSetOf(read, add, update, delete)))
                roleJpaRepo.save(RoleEntity(name = "USER", authorities = mutableSetOf(read, add, update)))
            }
        }
    }
}