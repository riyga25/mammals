package rusmammals.domain.repository

import rusmammals.domain.model.AuthParams
import rusmammals.domain.model.FeedbackParams
import rusmammals.domain.model.SpecieModel

interface DataRepository {
    suspend fun auth(params: AuthParams)
    suspend fun getSpecies(): List<SpecieModel>
    suspend fun sendFeedback(params: FeedbackParams)
}