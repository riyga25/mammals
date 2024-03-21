package com.app.rusmammals.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.app.rusmammals.R
import com.app.rusmammals.ui.components.MammalBackButton
import com.app.rusmammals.ui.components.MammalButton
import com.app.rusmammals.ui.components.MammalTopBar
import org.koin.androidx.compose.koinViewModel

object SettingsScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val viewModel: SettingsViewModel = koinViewModel()

        SettingsLayout(
            onBack = { navigator?.pop() },
            logout = { viewModel.logout() }
        )
    }
}

@Composable
private fun SettingsLayout(
    onBack: () -> Unit,
    logout: () -> Unit
) {
    Scaffold(
        topBar = {
            MammalTopBar(
                title = stringResource(R.string.menu_settings),
                navigationIcon = {
                    MammalBackButton { onBack.invoke() }
                }
            )
        }
    ) { paddings ->
        Column(
            Modifier
                .padding(paddings)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MammalButton(title = stringResource(R.string.logout)) {
                logout.invoke()
            }
        }
    }
}