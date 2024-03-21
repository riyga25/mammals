package rusmammals.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import rusmammals.domain.common.FlowUseCase
import rusmammals.domain.model.AuthParams
import rusmammals.domain.model.FeedbackParams
import rusmammals.domain.repository.DataRepository

interface SendFeedbackUseCase : FlowUseCase<FeedbackParams, Unit>

internal class SendFeedbackUseCaseImpl(
    private val repository: DataRepository
) : SendFeedbackUseCase {
    override fun execute(param: FeedbackParams): Flow<Result<Unit>> =
        flow {
            emit(Result.success(repository.sendFeedback(param)))
        }
}
