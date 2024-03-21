package com.app.rusmammals.ui.auth

import androidx.lifecycle.viewModelScope
import com.app.rusmammals.common.BaseViewModel
import com.app.rusmammals.common.UiEffect
import com.app.rusmammals.common.UiState
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import rusmammals.domain.model.AuthParams
import rusmammals.domain.usecases.AuthUseCase

class AuthViewModel(
    private val authUseCase: AuthUseCase
): BaseViewModel<AuthContract.State, AuthContract.Effect>() {

    override fun createInitialState(): AuthContract.State = AuthContract.State()

    fun auth(user: String, pass: String) {
        viewModelScope.launch {
            authUseCase(AuthParams(user, pass))
                .onStart { setState { copy(loading = true) } }
                .onCompletion { setState { copy(loading = false) } }
                .single()
                .onFailure {
                    setEffect { AuthContract.Effect.Error(it.message ?: "") }
                }
        }
    }
}

class AuthContract {
    data class State(
        val loading: Boolean = false
    ) : UiState

    sealed class Effect : UiEffect {
        class Error(val message: String): Effect()
    }
}