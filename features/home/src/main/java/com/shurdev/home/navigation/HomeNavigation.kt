package com.shurdev.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.shurdev.domain.models.Flower
import com.shurdev.home.HomeRoute
import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

fun NavController.navigateToHomeScreen() = navigate(HomeRoute)

fun NavGraphBuilder.homeScreen(
    onFlowerClick: (Flower) -> Unit,
) {
    composable<HomeRoute> {
        HomeRoute(
            onFlowerClick = onFlowerClick,
        )
    }
}