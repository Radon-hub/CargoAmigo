package org.radon.cargoamigo.auth.infrastructure.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.radon.cargoamigo.common.UserRole
import org.radon.cargoamigo.common.UserType
import java.util.UUID

@Entity
@Table(name = "users")
data class UserEntity(
    @Id
    @GeneratedValue(
        strategy = GenerationType.UUID
    )
    val id: UUID,
    @Column(nullable = false)
    val firstName: String,
    @Column(nullable = false)
    val lastName: String,
    @Column(nullable = false)
    val age: Byte,
    @Column(nullable = false,unique = true)
    val phoneNumber: String,
    @Column(nullable = false)
    val password: String,
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val role: UserRole,
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val type: UserType,
    @Column(nullable = false)
    val enabled: Boolean = true,
)
