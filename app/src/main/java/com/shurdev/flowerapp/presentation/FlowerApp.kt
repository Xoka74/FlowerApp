package com.shurdev.flowerapp.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.shurdev.flowerapp.presentation.composables.AppBottomNavigation
import com.shurdev.flowerapp.presentation.screens.StartupRoute
import com.shurdev.flowerapp.presentation.screens.startupScreen
import com.shurdev.flowerapp.presentation.viewModel.SettingsViewModel
import com.shurdev.gallery.navigation.GalleryNavGraph
import com.shurdev.gallery.navigation.galleryNavGraph
import com.shurdev.gallery.navigation.navigateToGalleryPlantDetailsScreen
import com.shurdev.my_plants.navigation.myPlantsNavGraph
import com.shurdev.my_plants.screens.create.navigation.navigateToMyPlantCreateScreen
import com.shurdev.my_plants.screens.edit.navigation.navigateToEditMyPlantScreen
import com.shurdev.onboarding.navigation.onboardingNavGraph
import com.shurdev.profile.navigation.profileNavGraph
import com.shurdev.recommended_plants.navigation.navigateToRecommendedPlantsGraph
import com.shurdev.recommended_plants.navigation.recommendedPlantsNavGraph
import com.shurdev.survey.navigation.SurveyNavGraph
import com.shurdev.survey.navigation.navigateToSurveyGraph
import com.shurdev.survey.navigation.surveyNavGraph
import navigateToMyPlantDetailsScreen

@Composable
fun FlowerApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentDestination = navBackStackEntry?.destination

    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem.MyPlants,
            BottomNavigationItem.Gallery,
            BottomNavigationItem.Profile,
        )
    }

    val selectedDestination = bottomNavigationItems.firstOrNull { item ->
        currentDestination?.hasRoute(item.route::class) == true
    }

    val settingsViewModel = hiltViewModel<SettingsViewModel>()

    Scaffold(
        bottomBar = {
            if (selectedDestination != null) {
                BottomAppBar {
                    AppBottomNavigation(
                        items = bottomNavigationItems,
                        selectedItem = selectedDestination,
                        onItemClick = { item ->
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            modifier = Modifier.padding(padding),
            navController = navController,
            startDestination = StartupRoute,
        ) {
            startupScreen(
                settingsViewModel = settingsViewModel,
                onStartupFinished = { settings ->
                    navController.navigate(GalleryNavGraph) {
                        popUpTo(StartupRoute) {
                            inclusive = true
                        }
                    }

                    if (settings.isFirstRun) {
                        navController.navigate(SurveyNavGraph)
                    }
                },
            )

            onboardingNavGraph(
                onFinishOnboarding = navController::navigateToSurveyGraph
            )

            surveyNavGraph(
                onFinishSurvey = navController::navigateUp
            )

            galleryNavGraph(
                onPop = navController::navigateUp,
                onPlantClick = { plant ->
                    plant.id?.let {
                        navController.navigateToGalleryPlantDetailsScreen(plantId = it)
                    }
                }
            )

            myPlantsNavGraph(
                onAddPlantClick = navController::navigateToMyPlantCreateScreen,
                onBackInvoked = navController::navigateUp,
                onPlantEditClick = { plant ->
                    navController.navigateToEditMyPlantScreen(plant.id)
                },
                onPlantClick = { plant ->
                    navController.navigateToMyPlantDetailsScreen(plant.id)
                },
            )

            profileNavGraph(
                onTakeSurveyClick = navController::navigateToSurveyGraph,
                onRecommendedPlantsClick = navController::navigateToRecommendedPlantsGraph,
                onSettingsClick = {
                    // TODO: Navigate to SettingsScreen
                },
            )

            recommendedPlantsNavGraph(
                onPlantClick = {
                    // TODO
                }
            )
        }
    }
}