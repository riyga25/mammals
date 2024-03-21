package rusmammals.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import rusmammals.domain.common.FlowUseCase
import rusmammals.domain.model.AuthParams
import rusmammals.domain.repository.DataRepository

interface AuthUseCase : FlowUseCase<AuthParams, Unit>

internal class AuthUseCaseImpl(
    private val repository: DataRepository
) : AuthUseCase {
    override fun execute(param: AuthParams): Flow<Result<Unit>> =
        flow {
            emit(Result.success(repository.auth(param)))
        }
}
