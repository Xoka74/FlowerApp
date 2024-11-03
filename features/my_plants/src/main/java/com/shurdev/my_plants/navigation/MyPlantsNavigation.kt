package com.shurdev.my_plants.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.shurdev.domain.models.Plant
import com.shurdev.domain.models.PlantId
import com.shurdev.my_plants.screens.details.MyPlantDetailsRoute
import com.shurdev.my_plants.screens.myPlants.MyPlantsRoute
import kotlinx.serialization.Serializable

@Serializable
object MyPlantsNavGraph

fun NavGraphBuilder.myPlantsNavGraph(
    onPlantClick: (Plant) -> Unit,
) {
    navigation<MyPlantsNavGraph>(
        startDestination = MyPlantsRoute
    ) {
        myPlantsScreen(
            onPlantClick = onPlantClick,
        )

        myPlantDetailsScreen()
    }
}

@Serializable
object MyPlantsRoute

fun NavGraphBuilder.myPlantsScreen(
    onPlantClick: (Plant) -> Unit,
) {
    composable<MyPlantsRoute> {
        MyPlantsRoute(
            onPlantClick = onPlantClick,
        )
    }
}

@Serializable
data class MyPlantDetails(val plantId: PlantId)

fun NavController.navigateToMyPlantDetailsScreen(plantId: PlantId) =
    navigate(MyPlantDetails(plantId))

fun NavGraphBuilder.myPlantDetailsScreen() {
    composable<MyPlantDetails> { backStackEntry ->
        val plantDetails: MyPlantDetails = backStackEntry.toRoute<MyPlantDetails>()

        MyPlantDetailsRoute(
            plantId = plantDetails.plantId
        )
    }
}
