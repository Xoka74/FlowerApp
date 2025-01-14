package com.shurdev.auth.presentation.ui.screens.login.viewModel

import androidx.lifecycle.viewModelScope
import com.shurdev.domain.repositories.AuthRepository
import com.shurdev.ui_kit.viewModel.base.BaseViewModel
import com.shurdev.utils.runSuspendCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : BaseViewModel<LoginUiState>(LoginIdleState()) {
    fun login() {
        updateUiState { LoginLoadingState }

        viewModelScope.launch {
            runSuspendCatching {
                authRepository.login()
            }.onSuccess {
                updateUiState { LoginSuccessState }
            }.onFailure {
                updateUiState { LoginIdleState(error = "Неизвестная ошибка") }
            }
        }
    }
}