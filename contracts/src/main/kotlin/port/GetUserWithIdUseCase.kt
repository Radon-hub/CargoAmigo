package port

import dto.UserContractDto
import java.util.UUID

interface GetUserWithIdUseCase {
    fun getWithId(id: UUID): UserContractDto
}