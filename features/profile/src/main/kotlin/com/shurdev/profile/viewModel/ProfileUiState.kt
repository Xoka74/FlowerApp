package com.shurdev.profile.viewModel

import androidx.compose.runtime.Stable
import com.shurdev.domain.models.user.User

sealed interface ProfileUiState

data object ProfileLoadingState : ProfileUiState

@Stable
data class ProfileLoadedState(val user: User) : ProfileUiState

@Stable
data object ProfileErrorState : ProfileUiState