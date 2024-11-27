package com.shurdev.recommended_plants.screens.recommendedPlants.viewModel

import com.shurdev.domain.models.Plant

sealed class RecommendedPlantsUiState

data object RecommendedPlantsLoadingState : RecommendedPlantsUiState()

data class RecommendedPlantsLoadedState(val plants: List<Plant>) : RecommendedPlantsUiState()

data object RecommendedPlantsLoadingErrorState : RecommendedPlantsUiState()
