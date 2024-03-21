package com.app.rusmammals.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.rusmammals.common.BaseViewModel
import com.app.rusmammals.common.UiEffect
import com.app.rusmammals.common.UiState
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import rusmammals.data.prefs.PreferencesDataSource
import rusmammals.domain.model.AuthParams
import rusmammals.domain.usecases.AuthUseCase

class SettingsViewModel(
    private val preferencesDataSource: PreferencesDataSource
): ViewModel() {

    fun logout() {
        viewModelScope.launch {
            preferencesDataSource.logout()
        }
    }
}