package rusmammals.data.model.dtos;

import kotlinx.serialization.Serializable

@Serializable
data class FeedbackDto(
    val email: String,
    val text: String
)
