package org.radon.cargoamigo.auth.infrastructure.entity

import jakarta.persistence.*
import lombok.AllArgsConstructor

@Entity
@Table(name = "authorities")
@AllArgsConstructor
 class AuthorityEntity{
    @Id
    @GeneratedValue
     var id: Long ? = null
     var authority: String? = null
    @ManyToMany
    @JoinTable(
        name = "role_authority",
        joinColumns = [JoinColumn(name = "role_id")],
        inverseJoinColumns = [JoinColumn(name = "authority_id")]
    )
     var roleEntities: MutableSet<RoleEntity>? = mutableSetOf()

    constructor(authority: String?, roleEntities: MutableSet<RoleEntity>?) {
        this.authority = authority
        this.roleEntities = roleEntities
    }
    constructor(authority: String) {
        this.authority = authority
    }
}
