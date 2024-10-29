package com.shurdev.my_plants.view_model

import androidx.compose.runtime.Stable
import com.shurdev.domain.models.Plant


@Stable
sealed class MyPlantDetailsUiState


@Stable
data object MyPlantDetailsLoadingState : MyPlantDetailsUiState()

@Stable
data class MyPlantDetailsLoadedState(val plant: Plant) : MyPlantDetailsUiState()

@Stable
data object MyPlantDetailsErrorState : MyPlantDetailsUiState()