package org.radon.userservice.infrastructure.entity

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor

@Entity
@Table(name = "roles")
 class RoleEntity{
    @Id
    @GeneratedValue
     var id: Long? = null
     var name: String? = null
    @ManyToMany
    @JoinTable(
        name = "role_authority",
        joinColumns = [JoinColumn(name = "role_id")],
        inverseJoinColumns = [JoinColumn(name = "authority_id")]
    )
     var authorities: MutableSet<AuthorityEntity>? = mutableSetOf()
    @OneToMany(mappedBy = "roleEntity", cascade = [CascadeType.ALL], orphanRemoval = true)
     var userEntity: MutableList<UserEntity?>? = null

    constructor()

    constructor(name: String?, authorities: MutableSet<AuthorityEntity>?, userEntity: MutableList<UserEntity?>?) {
        this.name = name
        this.authorities = authorities
        this.userEntity = userEntity
    }

    constructor(name: String?, authorities: MutableSet<AuthorityEntity>?) {
        this.name = name
        this.authorities = authorities
    }
}