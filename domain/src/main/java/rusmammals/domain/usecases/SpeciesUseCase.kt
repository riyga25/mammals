package rusmammals.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import rusmammals.domain.common.FlowUseCase
import rusmammals.domain.model.AuthParams
import rusmammals.domain.model.SpecieModel
import rusmammals.domain.repository.DataRepository

interface SpeciesUseCase : FlowUseCase<Unit, List<SpecieModel>>

internal class SpeciesUseCaseImpl(
    private val repository: DataRepository
) : SpeciesUseCase {
    override fun execute(param: Unit): Flow<Result<List<SpecieModel>>> =
        flow {
            emit(Result.success(repository.getSpecies()))
        }
}
