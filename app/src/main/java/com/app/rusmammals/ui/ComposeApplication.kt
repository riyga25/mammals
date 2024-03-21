package com.app.rusmammals.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.app.rusmammals.ui.auth.AuthScreen
import com.app.rusmammals.ui.main.MainScreen
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.drop
import org.koin.compose.koinInject
import rusmammals.data.prefs.PreferencesDataSource

@Composable
fun ComposeApplication() {
    val preferencesDS: PreferencesDataSource = koinInject()
    val userPrefs by preferencesDS.flow.collectAsStateWithLifecycle(initialValue = null)
    var navigator: Navigator? = null

    fun getInitialScreen(): Screen {
        return if (userPrefs?.token != null) {
            MainScreen
        } else AuthScreen
    }

    LaunchedEffect(Unit) {
        snapshotFlow { userPrefs?.token }
            .drop(1)
            .collectLatest {
                if (userPrefs?.token.isNullOrEmpty()) {
                    navigator?.replaceAll(AuthScreen)
                } else {
                    navigator?.replaceAll(MainScreen)
                }
            }
    }

    MaterialTheme {
        if (userPrefs != null) {
            Navigator(getInitialScreen()) {
                navigator = it
                CurrentScreen()
            }
        }
    }
}