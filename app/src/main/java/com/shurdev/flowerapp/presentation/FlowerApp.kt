package com.shurdev.flowerapp.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.shurdev.gallery.navigation.galleryScreen
import com.shurdev.myPlants.navigation.MyPlantsRoute
import com.shurdev.myPlants.navigation.myPlantsScreen

@Suppress("ktlint:compose:modifier-missing-check")
@Composable
fun FlowerApp() {
    val navController = rememberNavController()

    Scaffold { padding ->
        NavHost(
            modifier = Modifier.padding(padding),
            navController = navController,
            startDestination = MyPlantsRoute,
        ) {
            myPlantsScreen(
                onFlowerClick = {
                    // Navigate to details screen
                },
            )
            galleryScreen(
                onFlowerClick = {
                    // Navigate to details screen
                },
                onCategoryClick = {
                    // Filter by category
                },
            )
        }
    }
}
