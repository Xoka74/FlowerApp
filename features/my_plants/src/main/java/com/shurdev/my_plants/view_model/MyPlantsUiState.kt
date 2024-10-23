package com.shurdev.my_plants.view_model

import androidx.compose.runtime.Stable
import com.shurdev.domain.models.Flower

@Stable
sealed class MyPlantsUiState

@Stable
data object MyPlantsLoadingState : MyPlantsUiState()

@Stable
data class MyPlantsLoadedState(val flowers: List<Flower>) : MyPlantsUiState()

@Stable
data object MyPlantsLoadingErrorState : MyPlantsUiState()
