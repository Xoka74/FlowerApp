package com.shurdev.my_plants.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
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
object MyPlantDetailsRoute

fun NavController.navigateToMyPlantDetailsScreen() = navigate(MyPlantDetailsRoute)

fun NavGraphBuilder.myPlantDetailsScreen() {
    composable<MyPlantDetailsRoute> {
        MyPlantDetailsRoute()
    }
}
