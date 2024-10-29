package com.shurdev.my_plants.view_model

import androidx.compose.runtime.Stable
import com.shurdev.domain.models.Plant

@Stable
sealed class MyPlantsUiState

@Stable
data object MyPlantsLoadingState : MyPlantsUiState()

@Stable
data class MyPlantsLoadedState(val plants: List<Plant>) : MyPlantsUiState()

@Stable
data object MyPlantsLoadingErrorState : MyPlantsUiState()
