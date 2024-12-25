package com.shurdev.recommended_plants.navigation

import androidx.annotation.Keep
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.shurdev.domain.models.plant.Plant
import com.shurdev.recommended_plants.screens.recommendedPlants.RecommendedPlantsRoute
import kotlinx.serialization.Serializable

@Keep
@Serializable
object RecommendedPlantsNavGraph

fun NavController.navigateToRecommendedPlantsGraph() = navigate(RecommendedPlantsNavGraph)

fun NavGraphBuilder.recommendedPlantsNavGraph(
    onPlantClick: (Plant) -> Unit,
    onBackInvoked: () -> Unit,
) {
    navigation<RecommendedPlantsNavGraph>(
        startDestination = RecommendedPlantsRoute,
    ) {
        recommendedPlantsScreen(
            onPlantClick = onPlantClick,
            onBackInvoked = onBackInvoked,
        )
    }
}

@Keep
@Serializable
object RecommendedPlantsRoute

fun NavGraphBuilder.recommendedPlantsScreen(
    onPlantClick: (Plant) -> Unit,
    onBackInvoked: () -> Unit,
) {
    composable<RecommendedPlantsRoute> {
        RecommendedPlantsRoute(
            onPlantClick = onPlantClick,
            onBackInvoked = onBackInvoked,
        )
    }
}