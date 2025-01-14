package com.shurdev.trade.screens.plantPick.viewModel

import androidx.compose.runtime.Stable
import com.shurdev.trade.models.MyPlantPresentation

@Stable
sealed class PlantPickUiState

@Stable
data object PlantPickLoadingState : PlantPickUiState()

@Stable
data class PlantPickLoadedState(
    val plants: List<MyPlantPresentation>
) : PlantPickUiState()

@Stable
data object PlantPickLoadingErrorState : PlantPickUiState()
