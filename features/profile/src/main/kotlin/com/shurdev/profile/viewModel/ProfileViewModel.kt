package com.shurdev.profile.viewModel

import androidx.lifecycle.viewModelScope
import com.shurdev.domain.repositories.LogoutRepository
import com.shurdev.domain.repositories.MeRepository
import com.shurdev.ui_kit.viewModel.base.BaseViewModel
import com.shurdev.utils.runSuspendCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val meRepository: MeRepository,
    private val logoutRepository: LogoutRepository,
) : BaseViewModel<ProfileUiState>(ProfileLoadingState) {
    init {
        loadProfile()
    }

    fun loadProfile() {
        updateUiState { ProfileLoadingState }
        viewModelScope.launch {
            runSuspendCatching {
                val user = meRepository.getUser()
                updateUiState { ProfileLoadedState(user) }
            }.onFailure {
                updateUiState { ProfileErrorState }
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            logoutRepository.logout()
        }
    }
}