package com.shurdev.onboarding.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.shurdev.onboarding.OnboardingRoute
import kotlinx.serialization.Serializable

@Serializable
object OnboardingNavGraph

fun NavGraphBuilder.onboardingNavGraph(
    onFinishOnboarding: () -> Unit
) {
    navigation<OnboardingNavGraph>(
        startDestination = OnboardingRoute,
    ) {
        onboardingScreen(onFinishOnboarding = onFinishOnboarding)
    }
}


@Serializable
object OnboardingRoute

fun NavGraphBuilder.onboardingScreen(
    onFinishOnboarding: () -> Unit
) {
    composable<OnboardingRoute> {
        OnboardingRoute(
            onFinishOnboarding = onFinishOnboarding
        )
    }
}