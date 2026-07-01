package port

import dto.UserContractDto
import java.util.UUID

interface GetUserWithPhoneNumberUseCase {
    fun getWithPhoneNumber(phone: String): UserContractDto
}