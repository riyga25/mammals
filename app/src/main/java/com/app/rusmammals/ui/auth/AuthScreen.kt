package com.app.rusmammals.ui.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import com.app.rusmammals.R
import com.app.rusmammals.ui.components.FullScreenLoader
import com.app.rusmammals.ui.components.MammalButton
import com.app.rusmammals.ui.components.MammalTextField
import com.app.rusmammals.ui.components.MammalTopBar
import org.koin.androidx.compose.koinViewModel

object AuthScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel: AuthViewModel = koinViewModel()
        var user by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        val isEnterEnabled = user.isNotBlank() && password.isNotBlank()
        val state by viewModel.uiState.collectAsStateWithLifecycle()
        val effect by viewModel.effect.collectAsStateWithLifecycle(initialValue = null)
        val snackbarHostState = remember { SnackbarHostState() }
        val focusManager = LocalFocusManager.current

        LaunchedEffect(effect) {
            when (effect) {
                is AuthContract.Effect.Error -> {
                    snackbarHostState.showSnackbar((effect as AuthContract.Effect.Error).message)
                }

                else -> {}
            }
        }

        AuthLayout(
            snackbarHostState = snackbarHostState,
            onAuth = {
                focusManager.clearFocus()
                viewModel.auth(user, password)
            },
            isAuthEnabled = isEnterEnabled,
            loading = state.loading,
            userName = user,
            password = password,
            changePassword = { password = it },
            changeUserName = { user = it }
        )
    }
}

@Composable
private fun AuthLayout(
    snackbarHostState: SnackbarHostState,
    onAuth: () -> Unit,
    isAuthEnabled: Boolean,
    loading: Boolean,
    userName: String,
    password: String,
    changeUserName: (String) -> Unit,
    changePassword: (String) -> Unit,
) {
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            MammalTopBar(title = stringResource(R.string.app_name))
        }
    ) { paddings ->
        Box(
            modifier = Modifier
                .padding(paddings)
                .imePadding()
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(Modifier.padding(horizontal = 32.dp)) {
                MammalTextField(
                    value = userName,
                    onValueChange = changeUserName,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    label = stringResource(R.string.username)
                )
                Spacer(modifier = Modifier.size(8.dp))
                MammalTextField(
                    value = password,
                    onValueChange = changePassword,
                    visualTransformation = PasswordVisualTransformation(),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    label = stringResource(R.string.password)
                )
                Spacer(modifier = Modifier.size(24.dp))
                MammalButton(
                    title = stringResource(R.string.login),
                    enabled = isAuthEnabled,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    onAuth.invoke()
                }
            }
        }
    }
    FullScreenLoader(visible = loading)
}