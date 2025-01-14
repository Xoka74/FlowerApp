package com.shurdev.flowerapp.presentation.screens.splash.navigation

import androidx.annotation.Keep
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.shurdev.flowerapp.presentation.screens.splash.SplashRoute
import kotlinx.serialization.Serializable

@Keep
@Serializable
object SplashRoute

fun NavGraphBuilder.splashScreen() {
    composable<SplashRoute> {
        SplashRoute()
    }
}