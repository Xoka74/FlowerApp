package com.shurdev.auth.presentation.ui.screens.login

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.shurdev.auth.presentation.ui.screens.login.composables.SignInWithGoogleButton
import com.shurdev.auth.presentation.ui.screens.login.viewModel.LoginIdleState
import com.shurdev.auth.presentation.ui.screens.login.viewModel.LoginLoadingState
import com.shurdev.auth.presentation.ui.screens.login.viewModel.LoginSuccessState
import com.shurdev.auth.presentation.ui.screens.login.viewModel.LoginUiState
import com.shurdev.auth.presentation.ui.screens.login.viewModel.LoginViewModel
import com.shurdev.ui_kit.errors.ErrorView
import com.shurdev.ui_kit.layouts.Center
import com.shurdev.ui_kit.theme.FlowerAppTheme

@Composable
fun LoginRoute(
    onSuccessLogin: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel<LoginViewModel>(),
) {

    val uiState by viewModel.uiState.collectAsState()

    LoginScreen(
        uiState = uiState,
        onLoginClick = viewModel::login,
        onSuccessLogin = onSuccessLogin,
    )
}

@Composable
fun LoginScreen(
    onLoginClick: () -> Unit,
    onSuccessLogin: () -> Unit,
    uiState: LoginUiState,
) {
    LaunchedEffect(uiState) {
        if (uiState is LoginSuccessState) {
            onSuccessLogin()
        }
    }

    val showLoading = uiState is LoginLoadingState || uiState is LoginSuccessState
    val error = (uiState as? LoginIdleState)?.error

    LoginContent(
        onLoginClick = onLoginClick,
        isLoading = showLoading,
        error = error,
    )
}

@Composable
fun LoginContent(
    onLoginClick: () -> Unit,
    isLoading: Boolean,
    error: String? = null,
) {
    Center {
        AsyncImage(
            model = "https://avatar.iran.liara.run/public/15",
            contentDescription = null,
        )

        Spacer(Modifier.height(10.dp))

        SignInWithGoogleButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = onLoginClick,
            isLoading = isLoading,
        )

        if (error != null) {
            ErrorView(
                text = error,
            )
        }
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    FlowerAppTheme {
        Surface {
            LoginScreen(
                onSuccessLogin = {},
                onLoginClick = {},
                uiState = LoginIdleState(),
            )
        }
    }
}