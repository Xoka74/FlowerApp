package com.shurdev.my_plants.screens.details.viewModel

import androidx.compose.runtime.Stable
import com.shurdev.domain.models.myPlant.MyPlant


@Stable
sealed class MyPlantDetailsUiState

@Stable
data object MyPlantDetailsLoadingState : MyPlantDetailsUiState()

@Stable
data class MyPlantDetailsLoadedState(val plant: MyPlant) : MyPlantDetailsUiState()

@Stable
data object MyPlantDetailsErrorState : MyPlantDetailsUiState()

@Stable
data object MyPlantDeletedState : MyPlantDetailsUiState()