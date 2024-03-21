package com.app.rusmammals.ui.species

import androidx.lifecycle.viewModelScope
import com.app.rusmammals.common.BaseViewModel
import com.app.rusmammals.common.UiEffect
import com.app.rusmammals.common.UiState
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import rusmammals.domain.model.SpecieModel
import rusmammals.domain.usecases.SpeciesUseCase

class SpeciesViewModel(
    private val getSpeciesUseCase: SpeciesUseCase
): BaseViewModel<SpeciesContract.State, SpeciesContract.Effect>() {

    override fun createInitialState(): SpeciesContract.State = SpeciesContract.State()

    init {
        getSpecies()
    }

    fun getSpecies() {
        viewModelScope.launch {
            getSpeciesUseCase(Unit)
                .onStart { setState { copy(loading = true) } }
                .onCompletion { setState { copy(loading = false) } }
                .single()
                .onSuccess {
                    setState { copy(species = it.sortedBy { it.id }) }
                }
                .onFailure {
                    setEffect { SpeciesContract.Effect.Error(it.message ?: "") }
                }
        }
    }
}

class SpeciesContract {
    data class State(
        val loading: Boolean = false,
        val species: List<SpecieModel> = emptyList()
    ) : UiState

    sealed class Effect : UiEffect {
        class Error(val message: String): Effect()
    }
}