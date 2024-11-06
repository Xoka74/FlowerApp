package com.shurdev.profile.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shurdev.domain.repositories.UserRepository
import com.shurdev.utils.runSuspendCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow<ProfileUiState>(ProfileLoadingState)
    val uiState = _uiState.asStateFlow()

    init {
        loadProfile()
    }

    private fun loadProfile() {
        _uiState.update { ProfileLoadingState }
        viewModelScope.launch {
            runSuspendCatching {
                val user = userRepository.getUser()
                _uiState.update { ProfileLoadedState(user) }
            }.onFailure {
                _uiState.update { ProfileErrorState }
            }
        }
    }

    fun logout() {
        // TODO: Implement me
    }
}