package rusmammals.data.network

import rusmammals.data.model.dtos.AuthRequestDto
import rusmammals.data.model.dtos.FeedbackDto
import rusmammals.data.prefs.PreferencesDataSource
import rusmammals.domain.model.AuthParams
import rusmammals.domain.model.FeedbackParams
import rusmammals.domain.model.SpecieModel

internal class NetworkDataSource(
    private val api: NetworkApi,
    private val preferencesDataSource: PreferencesDataSource
) {
    suspend fun auth(params: AuthParams): String {
        return api.auth(AuthRequestDto(params.password, params.user)).token
    }

    suspend fun sendFeedback(params: FeedbackParams) {
        val token = preferencesDataSource.getToken() ?: return
        return api.postFeedback(token, FeedbackDto(params.email, params.text))
    }

    suspend fun getSpecies(): List<SpecieModel> {
        val token = preferencesDataSource.getToken() ?: return emptyList()
        return api.getSpecies(token = "Bearer $token").map { it.toDomain() }
    }
}