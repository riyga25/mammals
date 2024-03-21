package com.app.rusmammals.ui.species

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.app.rusmammals.ui.components.FullScreenLoader
import com.app.rusmammals.ui.components.MammalBackButton
import com.app.rusmammals.ui.components.MammalTopBar
import org.koin.androidx.compose.koinViewModel
import rusmammals.domain.model.SpecieModel

object SpeciesScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val viewModel: SpeciesViewModel = koinViewModel()
        val state by viewModel.uiState.collectAsStateWithLifecycle()
        val effect by viewModel.effect.collectAsStateWithLifecycle(initialValue = null)
        val snackbarHostState = remember { SnackbarHostState() }

        LaunchedEffect(effect) {
            when (effect) {
                is SpeciesContract.Effect.Error -> {
                    snackbarHostState.showSnackbar((effect as SpeciesContract.Effect.Error).message)
                }

                else -> {}
            }
        }

        SpeciesLayout(
            onBack = { navigator?.pop() },
            onItemClick = { navigator?.push(SpecieDetailScreen(it)) },
            state = state,
            snackbarHostState = snackbarHostState
        )
    }
}

@Composable
private fun SpeciesLayout(
    onBack: () -> Unit,
    onItemClick: (SpecieModel) -> Unit,
    state: SpeciesContract.State,
    snackbarHostState: SnackbarHostState
) {
    Scaffold(
        topBar = {
            MammalTopBar(
                title = "Список видов",
                navigationIcon = {
                    MammalBackButton { onBack.invoke() }
                }
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddings ->
        LazyColumn(
            modifier = Modifier.padding(top = paddings.calculateTopPadding()),
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                bottom = paddings.calculateBottomPadding(),
                top = 8.dp
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.species) {
                Item(it) {
                    onItemClick.invoke(it)
                }
            }
        }
        FullScreenLoader(visible = state.loading)
    }
}

@Composable
private fun Item(
    item: SpecieModel,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick.invoke() }
                .padding(8.dp)
        ) {
            Text(text = item.rusname)
            Spacer(modifier = Modifier.size(4.dp))
            Text(text = item.latName, fontSize = 12.sp)
        }
    }
}