package com.app.rusmammals.ui.feedback

import android.util.Patterns
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.app.rusmammals.R
import com.app.rusmammals.ui.components.FullScreenLoader
import com.app.rusmammals.ui.components.MammalBackButton
import com.app.rusmammals.ui.components.MammalButton
import com.app.rusmammals.ui.components.MammalTextField
import com.app.rusmammals.ui.components.MammalTopBar
import org.koin.androidx.compose.koinViewModel

object FeedbackScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val viewModel: FeedbackViewModel = koinViewModel()
        val state by viewModel.uiState.collectAsStateWithLifecycle()
        val effect by viewModel.effect.collectAsStateWithLifecycle(initialValue = null)
        val snackbarHostState = remember { SnackbarHostState() }
        val focusManager = LocalFocusManager.current
        var showSuccess by remember { mutableStateOf(false) }

        LaunchedEffect(effect) {
            when (effect) {
                is FeedbackContract.Effect.Error -> {
                    snackbarHostState.showSnackbar((effect as FeedbackContract.Effect.Error).message)
                }

                is FeedbackContract.Effect.Success -> {

                }

                else -> {}
            }
        }

        FeedbackLayout(
            onBack = { navigator?.pop() },
            onSendFeedback = { email, text ->
                focusManager.clearFocus()
                viewModel.sentFeedback(email, text)
            },
            snackbarHostState = snackbarHostState,
            showSuccess = showSuccess,
            loading = state.loading
        )
    }
}

@Composable
private fun FeedbackLayout(
    snackbarHostState: SnackbarHostState,
    onBack: () -> Unit,
    showSuccess: Boolean,
    loading: Boolean,
    onSendFeedback: (email: String, text: String) -> Unit
) {
    var message by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    val isEmailValid by remember(email) {
        mutableStateOf(Patterns.EMAIL_ADDRESS.matcher(email).matches())
    }

    Scaffold(
        topBar = {
            MammalTopBar(
                title = stringResource(R.string.menu_feedback),
                navigationIcon = {
                    MammalBackButton { onBack.invoke() }
                }
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddings ->
        if (showSuccess) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(text = stringResource(R.string.success_feedback))
            }
            return@Scaffold
        }

        Column(
            Modifier
                .padding(paddings)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(R.string.feedback_text))
            Spacer(modifier = Modifier.size(16.dp))
            MammalTextField(
                value = message,
                onValueChange = { message = it },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(R.string.feedback_marker)
            )
            Spacer(modifier = Modifier.size(8.dp))
            MammalTextField(
                value = email,
                onValueChange = { email = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                minLines = 5,
                singleLine = false,
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(R.string.feedback_email)
            )
            Spacer(modifier = Modifier.size(16.dp))
            MammalButton(
                title = stringResource(R.string.send),
                enabled = isEmailValid && message.isNotBlank()
            ) {
                onSendFeedback.invoke(email, message)
            }
        }
    }
    FullScreenLoader(visible = loading)
}