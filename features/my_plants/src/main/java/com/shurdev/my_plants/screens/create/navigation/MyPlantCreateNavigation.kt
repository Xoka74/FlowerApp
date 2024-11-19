package com.shurdev.my_plants.screens.create.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.shurdev.my_plants.screens.create.MyPlantCreateRoute
import kotlinx.serialization.Serializable

@Serializable
object MyPlantCreateRoute

fun NavController.navigateToMyPlantCreateScreen() = navigate(MyPlantCreateRoute)

fun NavGraphBuilder.myPlantCreateScreen(
    onBackInvoked: () -> Unit,
) {
    composable<MyPlantCreateRoute> {
        MyPlantCreateRoute(
            onBackInvoked = onBackInvoked,
        )
    }
}