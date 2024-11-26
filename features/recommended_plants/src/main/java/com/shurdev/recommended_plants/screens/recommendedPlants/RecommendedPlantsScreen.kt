package com.shurdev.recommended_plants.screens.recommendedPlants

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shurdev.domain.models.Plant
import com.shurdev.recommended_plants.R
import com.shurdev.recommended_plants.composables.RecommendedPlantsList
import com.shurdev.recommended_plants.screens.recommendedPlants.viewModel.RecommendedPlantsLoadedState
import com.shurdev.recommended_plants.screens.recommendedPlants.viewModel.RecommendedPlantsLoadingErrorState
import com.shurdev.recommended_plants.screens.recommendedPlants.viewModel.RecommendedPlantsLoadingState
import com.shurdev.recommended_plants.screens.recommendedPlants.viewModel.RecommendedPlantsUiState
import com.shurdev.recommended_plants.screens.recommendedPlants.viewModel.RecommendedPlantsViewModel

@Composable
internal fun RecommendedPlantsRoute(
    onPlantClick: (Plant) -> Unit
) {
    val viewModel = hiltViewModel<RecommendedPlantsViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    RecommendedPlantsScreen(
        uiState = uiState,
        onPlantClick = onPlantClick
    )
}

@Composable
internal fun RecommendedPlantsScreen(
    uiState: RecommendedPlantsUiState,
    onPlantClick: (Plant) -> Unit
) {

    Scaffold { padding ->
        when (uiState) {

            is RecommendedPlantsLoadedState -> {
                Column {
                    Text(
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .padding(horizontal = 16.dp),
                        text = stringResource(R.string.recommended_flowers),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )

                    RecommendedPlantsList(
                        plants = uiState.plants,
                        onPlantClick = onPlantClick
                    )
                }
            }

            is RecommendedPlantsLoadingState -> {}
            is RecommendedPlantsLoadingErrorState -> {}
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
                imageLink = ""
            )
        }),
        onPlantClick = {}
    )
}