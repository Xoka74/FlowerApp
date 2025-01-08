package com.shurdev.profile.viewModel

import androidx.lifecycle.viewModelScope
import com.shurdev.domain.repositories.UserRepository
import com.shurdev.ui_kit.viewModel.base.BaseViewModel
import com.shurdev.utils.runSuspendCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : BaseViewModel<ProfileUiState>(ProfileLoadingState) {
    init {
        loadProfile()
    }

    private fun loadProfile() {
        updateUiState { ProfileLoadingState }
        viewModelScope.launch {
            runSuspendCatching {
                val user = userRepository.getUser()
                updateUiState { ProfileLoadedState(user) }
            }.onFailure {
                updateUiState { ProfileErrorState }
            }
        }
    }

    fun logout() {
        // TODO: Implement me
    }
}