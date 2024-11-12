package com.shurdev.profile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.shurdev.profile.ProfileRoute
import kotlinx.serialization.Serializable


@Serializable
object ProfileNavGraph

fun NavGraphBuilder.profileNavGraph(
    onTakeSurveyClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
) {
    navigation<ProfileNavGraph>(
        startDestination = ProfileRoute,
    ) {
        profileRoute(
            onTakeSurveyClick = onTakeSurveyClick,
            onSettingsClick = onSettingsClick,
        )
    }
}

@Serializable
object ProfileRoute

fun NavGraphBuilder.profileRoute(
    onTakeSurveyClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
) {
    composable<ProfileRoute> {
        ProfileRoute(
            onTakeSurveyClick = onTakeSurveyClick,
            onSettingsClick = onSettingsClick,
        )
    }
}