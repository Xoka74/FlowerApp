package com.shurdev.my_plants.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.shurdev.domain.models.Flower
import com.shurdev.my_plants.MyPlantsRoute
import kotlinx.serialization.Serializable

@Serializable
object MyPlantsRoute

fun NavController.navigateToMyPlants() = navigate(MyPlantsRoute)

fun NavGraphBuilder.myPlantsScreen(
    onFlowerClick: (Flower) -> Unit,
) {
    composable<MyPlantsRoute> {
        MyPlantsRoute(
            onFlowerClick = onFlowerClick,
        )
    }
}