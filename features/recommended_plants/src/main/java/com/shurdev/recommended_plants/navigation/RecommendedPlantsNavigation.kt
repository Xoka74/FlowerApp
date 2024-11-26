package com.shurdev.recommended_plants.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.shurdev.domain.models.Plant
import com.shurdev.recommended_plants.screens.recommendedPlants.RecommendedPlantsRoute
import kotlinx.serialization.Serializable

@Serializable
object RecommendedPlantsNavGraph

fun NavController.navigateToRecommendedPlantsGraph() = navigate(RecommendedPlantsNavGraph)

fun NavGraphBuilder.recommendedPlantsNavGraph(
    onPlantClick: (Plant) -> Unit
) {
    navigation<RecommendedPlantsNavGraph>(
        startDestination = RecommendedPlantsRoute,
    ) {
        recommendedPlantsScreen(
            onPlantClick = onPlantClick
        )
    }
}


@Serializable
object RecommendedPlantsRoute

fun NavGraphBuilder.recommendedPlantsScreen(
    onPlantClick: (Plant) -> Unit
) {
    composable<RecommendedPlantsRoute> {
        RecommendedPlantsRoute(
            onPlantClick = onPlantClick
        )
    }
}