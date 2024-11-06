package com.shurdev.profile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.shurdev.profile.ProfileRoute
import kotlinx.serialization.Serializable


@Serializable
object ProfileNavGraph

fun NavGraphBuilder.profileNavGraph() {
    navigation<ProfileNavGraph>(
        startDestination = ProfileRoute,
    ) {
        profileRoute()
    }
}

@Serializable
object ProfileRoute

fun NavGraphBuilder.profileRoute() {
    composable<ProfileRoute> {
        ProfileRoute()
    }
}