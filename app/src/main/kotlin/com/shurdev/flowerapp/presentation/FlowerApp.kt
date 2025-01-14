package com.shurdev.flowerapp.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.shurdev.auth.base.AuthViewModel
import com.shurdev.auth.presentation.ui.screens.login.navigation.LoginRoute
import com.shurdev.auth.presentation.ui.screens.login.navigation.loginScreen
import com.shurdev.flowerapp.presentation.composables.AppBottomNavigation
import com.shurdev.flowerapp.presentation.screens.splash.navigation.SplashRoute
import com.shurdev.flowerapp.presentation.screens.splash.navigation.splashScreen
import com.shurdev.gallery.navigation.GalleryRoute
import com.shurdev.gallery.navigation.galleryNavGraph
import com.shurdev.gallery.navigation.navigateToGalleryPlantDetailsScreen
import com.shurdev.my_plants.navigation.myPlantsNavGraph
import com.shurdev.my_plants.screens.create.navigation.navigateToMyPlantCreateScreen
import com.shurdev.my_plants.screens.details.navigation.navigateToMyPlantDetailsScreen
import com.shurdev.my_plants.screens.edit.navigation.navigateToEditMyPlantScreen
import com.shurdev.onboarding.navigation.onboardingNavGraph
import com.shurdev.profile.navigation.profileNavGraph
import com.shurdev.recommended_plants.navigation.navigateToRecommendedPlantsGraph
import com.shurdev.recommended_plants.navigation.recommendedPlantsNavGraph
import com.shurdev.settings.viewModel.navigation.navigateToSettings
import com.shurdev.settings.viewModel.navigation.settingsScreen
import com.shurdev.survey.navigation.navigateToSurveyGraph
import com.shurdev.survey.navigation.surveyNavGraph
import com.shurdev.trade.navigation.navigateToCreateTradeScreen
import com.shurdev.trade.navigation.navigateToTradeDetailsScreen
import com.shurdev.trade.navigation.navigateToTradeGraph
import com.shurdev.trade.navigation.tradeNavGraph

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

    val authViewModel = hiltViewModel<AuthViewModel>()

    val isAuthorizedState by authViewModel.isAuthenticated.collectAsState()

    Scaffold(
        bottomBar = {
            if (selectedDestination != null) {
                BottomAppBar {
                    AppBottomNavigation(
                        items = bottomNavigationItems,
                        selectedItem = selectedDestination,
                        onItemClick = { item ->
                            navController.navigate(item.route) {
                                popUpTo(selectedDestination.route) {
                                    saveState = true
                                    inclusive = true
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
            startDestination = SplashRoute,
        ) {
            splashScreen()

            loginScreen()

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
                onTradeClick = navController::navigateToTradeGraph,
                onSettingsClick = navController::navigateToSettings,
            )

            recommendedPlantsNavGraph(
                onBackInvoked = navController::navigateUp,
                onPlantClick = {
                    // TODO
                }
            )

            tradeNavGraph(
                onBackInvoked = navController::navigateUp,
                onCreateTradeClick = navController::navigateToCreateTradeScreen,
                onTradeItemClick = { trade ->
                    trade.id?.let {
                        navController.navigateToTradeDetailsScreen(tradeId = it)
                    }
                },
            )

            settingsScreen(
                onDismiss = navController::navigateUp,
            )
        }

        LaunchedEffect(isAuthorizedState) {
            val route: Any = when (isAuthorizedState) {
                true -> GalleryRoute
                false -> LoginRoute
                null -> SplashRoute
            }

            navController.navigate(route) {
                popUpTo(navController.graph.startDestinationId) {
                    inclusive = true
                }
            }
        }
    }
}
