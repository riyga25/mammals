package com.app.rusmammals.di

import com.app.rusmammals.ui.auth.AuthViewModel
import com.app.rusmammals.ui.feedback.FeedbackViewModel
import com.app.rusmammals.ui.settings.SettingsViewModel
import com.app.rusmammals.ui.species.SpeciesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { AuthViewModel(get()) }
    viewModel { SettingsViewModel(get()) }
    viewModel { SpeciesViewModel(get()) }
    viewModel { FeedbackViewModel(get()) }
}