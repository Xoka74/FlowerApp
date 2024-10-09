package com.shurdev.flowerapp.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.shurdev.home.navigation.HomeRoute
import com.shurdev.home.navigation.homeScreen

@Composable
fun FlowerApp() {
    val navController = rememberNavController()

    Scaffold { padding ->
        NavHost(
            modifier = Modifier.padding(padding),
            navController = navController,
            startDestination = HomeRoute,
        ) {
            homeScreen(
                onFlowerClick = {
                    // Navigate to details screen
                }
            )
        }
    }
}