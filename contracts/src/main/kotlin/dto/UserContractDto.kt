package dto

import org.radon.cargoamigo.common.UserType
import java.util.UUID

data class UserContractDto(
    val id: UUID? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val age: Byte? = null,
    val phoneNumber: String? = null,
    val type: UserType? = null,
    val enabled: Boolean = true,
)
