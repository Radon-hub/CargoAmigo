package org.radon.application.user.infrastructure

import org.radon.application.user.application.port.out.UserRepository
import org.radon.application.user.domain.Authority
import org.radon.application.user.domain.User
import org.radon.application.user.infrastructure.entity.UserEntity
import org.radon.application.user.infrastructure.jpa.RoleJpaRepository
import org.radon.application.user.infrastructure.jpa.UserJpaRepository
import org.radon.cargoamigo.common.exceptionHandling.RequestMustNotBeNullException
import org.radon.cargoamigo.common.UserType
import org.radon.application.user.common.exceptionHandling.UserNotFoundException
import org.radon.application.user.domain.Role
import org.radon.cargoamigo.common.exceptionHandling.DuplicateUserException
import org.radon.cargoamigo.common.exceptionHandling.PhoneNumberCanNotBeNullException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImp(
    private val userJpaRepository: UserJpaRepository,
    private val passwordEncoder: PasswordEncoder,
    private val roleJpaRepository: RoleJpaRepository
)  : UserRepository {


    override fun createUser(user: User?): User? {

        requireNotNull(user){
            throw RequestMustNotBeNullException()
        }
        requireNotNull(user.phoneNumber){
            throw PhoneNumberCanNotBeNullException()
        }

        userJpaRepository.findUserByPhoneNumber(
            user.phoneNumber
        ).ifPresent { throw DuplicateUserException() }

        val pass = passwordEncoder.encode(user.password)

        val role = roleJpaRepository.getByName("USER")

        userJpaRepository.save(UserEntity(
            firstName = user.firstName ?: "",
            lastName = user.lastName ?: "",
            phoneNumber = user.phoneNumber,
            age = user.age ?: 0,
            password = pass!!,
            roleEntity = role!!,
            type = user.type ?: UserType.EMPLOYER,
            enabled = true,
        ))

        return user.copy(passwordHash = passwordEncoder.encode(user.password))

    }

    override fun loadUserByUsername(username: String): UserDetails {

        val user = userJpaRepository.findUserByPhoneNumber(username)

        if (!user.isPresent) {
            throw UserNotFoundException()
        }

        user.get().let {
            val authorities = it.roleEntity?.authorities?.map { authorityEntity ->
                Authority(
                    authorityName = authorityEntity.authority!!,
                )
            } ?: emptyList()

            return User(
                id = it.id,
                firstName = it.firstName,
                lastName = it.lastName,
                age = it.age,
                phoneNumber = it.phoneNumber,
                passwordHash = it.password,
                role = Role(it.roleEntity?.name, authorities.toSet()),
                type = UserType.DRIVER,
                enabled = true
            )

        }

    }
}