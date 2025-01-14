package com.shurdev.recommended_plants.screens.recommendedPlants

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.shurdev.domain.models.plant.Plant
import com.shurdev.recommended_plants.R
import com.shurdev.recommended_plants.composables.RecommendedPlantsList
import com.shurdev.recommended_plants.screens.recommendedPlants.viewModel.RecommendedPlantsLoadedState
import com.shurdev.recommended_plants.screens.recommendedPlants.viewModel.RecommendedPlantsLoadingErrorState
import com.shurdev.recommended_plants.screens.recommendedPlants.viewModel.RecommendedPlantsLoadingState
import com.shurdev.recommended_plants.screens.recommendedPlants.viewModel.RecommendedPlantsUiState
import com.shurdev.recommended_plants.screens.recommendedPlants.viewModel.RecommendedPlantsViewModel
import com.shurdev.ui_kit.layouts.DefaultScreenLayout

@Composable
internal fun RecommendedPlantsRoute(
    onPlantClick: (Plant) -> Unit,
    onBackInvoked: () -> Unit,
) {
    val viewModel = hiltViewModel<RecommendedPlantsViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    RecommendedPlantsScreen(
        uiState = uiState,
        onPlantClick = onPlantClick,
        onBackInvoked = onBackInvoked,
    )
}

@Composable
internal fun RecommendedPlantsScreen(
    uiState: RecommendedPlantsUiState,
    onPlantClick: (Plant) -> Unit,
    onBackInvoked: () -> Unit,
) {

    DefaultScreenLayout(
        title = stringResource(R.string.recommendations),
        onBackInvoked = onBackInvoked,
    ) {
        when (uiState) {
            is RecommendedPlantsLoadingState -> {}
            is RecommendedPlantsLoadingErrorState -> {}
            is RecommendedPlantsLoadedState -> {
                Column {
                    RecommendedPlantsList(
                        plants = uiState.plants,
                        onPlantClick = onPlantClick
                    )
                }
            }
        }
    }
}


@Preview
@Composable
internal fun RecommendedPlantsPreview() {
    RecommendedPlantsScreen(
        uiState = RecommendedPlantsLoadedState(plants = (1..10).map {
            Plant(
                id = it,
                name = "Name$it",
                description = "Description$it",
            )
        }),
        onPlantClick = {},
        onBackInvoked = {}
    )
}