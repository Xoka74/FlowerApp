package com.shurdev.flowerapp.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.shurdev.flowerapp.presentation.viewModel.SettingsErrorState
import com.shurdev.flowerapp.presentation.viewModel.SettingsLoadedState
import com.shurdev.flowerapp.presentation.viewModel.SettingsViewModel
import com.shurdev.ui_kit.errors.ErrorView
import com.shurdev.ui_kit.loaders.Loader
import kotlinx.serialization.Serializable

@Serializable
data object StartupRoute

fun NavGraphBuilder.startupScreen(
    onStartupFinished: (SettingsLoadedState) -> Unit,
    settingsViewModel: SettingsViewModel,
) {
    composable<StartupRoute> {
        StartupRoute(
            onStartupFinished = onStartupFinished,
            settingsViewModel = settingsViewModel
        )
    }
}

@Composable
internal fun StartupRoute(
    onStartupFinished: (SettingsLoadedState) -> Unit,
    settingsViewModel: SettingsViewModel,
) {
    val state by settingsViewModel.state.collectAsState()

    LaunchedEffect(state) {
        (state as? SettingsLoadedState)?.let(onStartupFinished)
    }

    when (state) {
        SettingsErrorState -> ErrorView()
        else -> Loader()
    }
}