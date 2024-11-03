package com.shurdev.my_plants.screens.myPlants.viewModel

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
