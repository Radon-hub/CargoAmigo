package port

import dto.UserContractDto

interface GetContextUserUseCase {
    fun getUser(): UserContractDto
}