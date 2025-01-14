package com.shurdev.profile.viewModel

import androidx.compose.runtime.Stable
import com.shurdev.domain.models.user.MeUser

sealed interface ProfileUiState

data object ProfileLoadingState : ProfileUiState

@Stable
data class ProfileLoadedState(val user: MeUser) : ProfileUiState

@Stable
data object ProfileErrorState : ProfileUiState