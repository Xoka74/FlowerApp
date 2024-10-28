package com.shurdev.my_plants.view_model

import androidx.compose.runtime.Stable
import com.shurdev.domain.models.Flower


@Stable
sealed class MyPlantDetailsUiState


@Stable
data object MyPlantDetailsLoadingState : MyPlantDetailsUiState()

@Stable
data class MyPlantDetailsLoadedState(val flower: Flower) : MyPlantDetailsUiState()

@Stable
data object MyPlantDetailsErrorState : MyPlantDetailsUiState()