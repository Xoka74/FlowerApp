package com.shurdev.flowerapp.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.shurdev.flowerapp.presentation.composables.AppBottomNavigation
import com.shurdev.gallery.navigation.galleryNavGraph
import com.shurdev.gallery.navigation.navigateToGalleryFlowerDetailsScreen
import com.shurdev.my_plants.navigation.MyPlantsNavGraph
import com.shurdev.my_plants.navigation.myPlantsNavGraph
import com.shurdev.my_plants.navigation.navigateToMyPlantDetailsScreen

@Composable
fun FlowerApp() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomAppBar {
                AppBottomNavigation(navController)
            }
        }
    ) { padding ->
        NavHost(
            modifier = Modifier.padding(padding),
            navController = navController,
            startDestination = MyPlantsNavGraph,
        ) {

            galleryNavGraph(
                onFlowerClick = {
                    navController.navigateToGalleryFlowerDetailsScreen()
                }
            )

            myPlantsNavGraph(
                onFlowerClick = {
                    navController.navigateToMyPlantDetailsScreen()
                }
            )
        }
    }
}