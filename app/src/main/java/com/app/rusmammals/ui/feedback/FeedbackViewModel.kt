package com.app.rusmammals.ui.feedback

import androidx.lifecycle.viewModelScope
import com.app.rusmammals.common.BaseViewModel
import com.app.rusmammals.common.UiEffect
import com.app.rusmammals.common.UiState
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import rusmammals.domain.model.FeedbackParams
import rusmammals.domain.usecases.SendFeedbackUseCase

class FeedbackViewModel(
    private val sendFeedbackUseCase: SendFeedbackUseCase
): BaseViewModel<FeedbackContract.State, FeedbackContract.Effect>() {

    override fun createInitialState(): FeedbackContract.State = FeedbackContract.State()

    fun sentFeedback(email: String, text: String) {
        val params = FeedbackParams(
            email = email,
            text = text
        )
        viewModelScope.launch {
            sendFeedbackUseCase(params)
                .onStart { setState { copy(loading = true) } }
                .onCompletion { setState { copy(loading = false) } }
                .single()
                .onSuccess {
                    setEffect { FeedbackContract.Effect.Success() }
                }
                .onFailure {
                    setEffect { FeedbackContract.Effect.Error(it.message ?: "") }
                }
        }
    }
}

class FeedbackContract {
    data class State(
        val loading: Boolean = false
    ) : UiState

    sealed class Effect : UiEffect {
        class Error(val message: String): Effect()
        class Success: Effect()
    }
}