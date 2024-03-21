package rusmammals.data.repository

import rusmammals.data.network.NetworkDataSource
import rusmammals.data.prefs.PreferencesDataSource
import rusmammals.domain.model.AuthParams
import rusmammals.domain.model.FeedbackParams
import rusmammals.domain.model.SpecieModel
import rusmammals.domain.repository.DataRepository

internal class DataRepositoryImpl(
    private val networkDataSource: NetworkDataSource,
    private val preferencesDataSource: PreferencesDataSource
) : DataRepository {
    override suspend fun auth(params: AuthParams) {
        val token = networkDataSource.auth(params)
        preferencesDataSource.updateToken(token)
    }

    override suspend fun getSpecies(): List<SpecieModel> {
        return networkDataSource.getSpecies()
    }

    override suspend fun sendFeedback(params: FeedbackParams) {
        networkDataSource.sendFeedback(params)
    }
}