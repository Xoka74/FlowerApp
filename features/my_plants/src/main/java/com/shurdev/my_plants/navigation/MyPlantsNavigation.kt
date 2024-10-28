package com.shurdev.my_plants.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.shurdev.domain.models.Flower
import com.shurdev.my_plants.MyPlantDetailsRoute
import com.shurdev.my_plants.MyPlantsRoute
import kotlinx.serialization.Serializable

@Serializable
object MyPlantsNavGraph

fun NavController.navigateToMyPlantsNavGraph() = navigate(MyPlantsNavGraph)

fun NavGraphBuilder.myPlantsNavGraph(
    onFlowerClick: (Flower) -> Unit
) {
    navigation<MyPlantsNavGraph>(
        startDestination = MyPlantsRoute
    ) {
        myPlantsScreen(
            onFlowerClick = onFlowerClick,
        )

        myPlantDetailsScreen()
    }
}

@Serializable
object MyPlantsRoute

fun NavController.navigateToMyPlantsScreen() = navigate(MyPlantsRoute)

fun NavGraphBuilder.myPlantsScreen(
    onFlowerClick: (Flower) -> Unit,
) {
    composable<MyPlantsRoute> {
        MyPlantsRoute(
            onFlowerClick = onFlowerClick,
        )
    }
}

@Serializable
data class MyPlantDetails(
    val plantId: Int
)

fun NavController.navigateToMyPlantDetailsScreen(plantId: Int) =
    navigate(MyPlantDetails(plantId))

fun NavGraphBuilder.myPlantDetailsScreen() {
    composable<MyPlantDetails> { backStackEntry ->
        val plantDetails: MyPlantDetails = backStackEntry.toRoute<MyPlantDetails>()

        MyPlantDetailsRoute(
            plantId = plantDetails.plantId
        )
    }
}
