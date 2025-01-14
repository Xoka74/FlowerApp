package com.shurdev.auth.presentation.ui.screens.login.viewModel

sealed interface LoginUiState

data class LoginIdleState(
    val error: String? = null,
) : LoginUiState

data object LoginLoadingState : LoginUiState

data object LoginSuccessState : LoginUiState
