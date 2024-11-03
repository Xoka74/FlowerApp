package com.shurdev.onboarding.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.shurdev.onboarding.OnboardingRoute
import kotlinx.serialization.Serializable

@Serializable
object OnboardingNavGraph

fun NavController.navigateToGalleryGraph() = navigate(OnboardingNavGraph)

fun NavGraphBuilder.onboardingNavGraph() {
    navigation<OnboardingNavGraph>(
        startDestination = OnboardingRoute,
    ) {
        onboardingScreen()
    }
}


@Serializable
object OnboardingRoute

fun NavController.navigateToGalleryScreen() = navigate(OnboardingRoute)

fun NavGraphBuilder.onboardingScreen() {
    composable<OnboardingRoute> {
        OnboardingRoute()
    }
}