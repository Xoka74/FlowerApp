package com.shurdev.settings.viewModel.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.shurdev.settings.viewModel.SettingsRoute
import kotlinx.serialization.Serializable

@Serializable
object SettingsRoute

fun NavController.navigateToSettings() = navigate(SettingsRoute)

fun NavGraphBuilder.settingsScreen(
    onDismiss: () -> Unit,
) {
    composable<SettingsRoute> {
        SettingsRoute(
            onDismiss = onDismiss,
        )
    }
}