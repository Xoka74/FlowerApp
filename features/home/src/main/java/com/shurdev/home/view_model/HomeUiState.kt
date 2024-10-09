package com.shurdev.home.view_model

import androidx.compose.runtime.Stable
import com.shurdev.domain.models.Flower

@Stable
sealed class HomeUiState

@Stable
data object HomeLoadingState : HomeUiState()

@Stable
data class HomeLoadedState(val flowers: List<Flower>) : HomeUiState()

@Stable
data object HomeLoadingErrorState : HomeUiState()
