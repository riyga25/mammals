package rusmammals.domain.common

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

interface FlowUseCase<in P, R> {
    operator fun invoke(param: P): Flow<Result<R>> = execute(param).handleOn()

    fun execute(param: P): Flow<Result<R>>

    fun <T> Flow<Result<T>>.handleOn(dispatcher: CoroutineDispatcher = Dispatchers.IO): Flow<Result<T>> {
        return this
            .catch { e ->
                Log.e("error", e.message ?: e.localizedMessage)
                emit(Result.failure(e))
            }
            .flowOn(dispatcher)
    }
}
