package rusmammals.data.model.dtos;

import kotlinx.serialization.Serializable

@Serializable
data class AuthRequestDto(
    val password: String,
    val username: String
)
