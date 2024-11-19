package com.shurdev.my_plants.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.shurdev.domain.models.MyPlant
import com.shurdev.my_plants.screens.create.navigation.myPlantCreateScreen
import com.shurdev.my_plants.screens.myPlants.MyPlantsRoute
import kotlinx.serialization.Serializable
import myPlantDetailsScreen

@Serializable
object MyPlantsNavGraph

fun NavGraphBuilder.myPlantsNavGraph(
    onPlantClick: (MyPlant) -> Unit,
    onAddPlantClick: () -> Unit,
    onBackInvoked: () -> Unit,
) {
    navigation<MyPlantsNavGraph>(
        startDestination = MyPlantsRoute
    ) {
        myPlantsScreen(
            onPlantClick = onPlantClick,
            onAddPlantClick = onAddPlantClick,
        )

        myPlantDetailsScreen(
            onBackInvoked = onBackInvoked,
        )
        myPlantCreateScreen(
            onBackInvoked = onBackInvoked,
        )
    }
}

@Serializable
object MyPlantsRoute

fun NavGraphBuilder.myPlantsScreen(
    onPlantClick: (MyPlant) -> Unit,
    onAddPlantClick: () -> Unit,
) {
    composable<MyPlantsRoute> {
        MyPlantsRoute(
            onPlantClick = onPlantClick,
            onAddPlantClick = onAddPlantClick,
        )
    }
}