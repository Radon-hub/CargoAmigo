package org.radon.userservice.infrastructure.adapter

import dto.UserContractDto
import org.radon.cargoamigo.common.exceptionHandling.UserNotFoundException
import org.radon.userservice.infrastructure.jpa.UserJpaRepository
import org.springframework.stereotype.Component
import port.GetUserWithIdUseCase
import port.GetUserWithPhoneNumberUseCase
import java.util.UUID

@Component
class UserAdapter(
    private val userJpaRepository: UserJpaRepository
) : GetUserWithIdUseCase, GetUserWithPhoneNumberUseCase {
    override fun getWithId(id: UUID): UserContractDto {
        val user = userJpaRepository.findUserById(id).orElseThrow { UserNotFoundException() }
        return UserContractDto(
            user.id,
            user.phoneNumber,
            user.phoneNumber,
            user.age,
            user.phoneNumber,
            user.type,
            user.enabled,
        )
    }

    override fun getWithPhoneNumber(phone: String): UserContractDto {
        val user = userJpaRepository.findUserByPhoneNumber(phone).orElseThrow { UserNotFoundException() }
        return UserContractDto(
            user.id,
            user.phoneNumber,
            user.phoneNumber,
            user.age,
            user.phoneNumber,
            user.type,
            user.enabled,
        )
    }
}