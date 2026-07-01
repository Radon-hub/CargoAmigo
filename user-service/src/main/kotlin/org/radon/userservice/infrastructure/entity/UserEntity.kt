package org.radon.userservice.infrastructure.entity

import jakarta.persistence.*
import lombok.AllArgsConstructor
import org.radon.cargoamigo.common.UserType
import java.util.*

@Entity
@Table(name = "users")
 class UserEntity{

    @Id
    @GeneratedValue(
        strategy = GenerationType.UUID
    )
     var id: UUID? = null
    @Column(nullable = false)
     var firstName: String = ""
    @Column(nullable = false)
     var lastName: String = ""
    @Column(nullable = false)
     var age: Byte = 0
    @Column(nullable = false,unique = true)
     var phoneNumber: String = ""
    @Column(nullable = false)
     var password: String = ""
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
     var roleEntity: RoleEntity? = null
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
     var type: UserType = UserType.EMPLOYER
    @Column(nullable = false)
     var enabled: Boolean = true
    constructor(firstName: String, lastName: String, age: Byte, phoneNumber: String, password: String, roleEntity: RoleEntity, type: UserType, enabled: Boolean) {
        this.firstName = firstName
        this.lastName = lastName
        this.age = age
        this.phoneNumber = phoneNumber
        this.password = password
        this.roleEntity = roleEntity
        this.type = type
        this.enabled = enabled
    }
    constructor()
}
