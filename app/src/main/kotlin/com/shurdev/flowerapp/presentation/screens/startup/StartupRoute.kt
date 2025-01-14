package com.shurdev.flowerapp.presentation.screens.startup

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.shurdev.flowerapp.presentation.screens.startup.viewModel.StartupErrorState
import com.shurdev.flowerapp.presentation.screens.startup.viewModel.StartupLoadedState
import com.shurdev.flowerapp.presentation.screens.startup.viewModel.StartupViewModel
import com.shurdev.ui_kit.errors.ErrorView
import com.shurdev.ui_kit.loaders.Loader
import kotlinx.serialization.Serializable

@Serializable
data object StartupRoute

fun NavGraphBuilder.startupScreen(
    onStartupFinished: (StartupLoadedState) -> Unit,
) {
    composable<StartupRoute> {
        StartupRoute(
            onStartupFinished = onStartupFinished,
        )
    }
}

@Composable
internal fun StartupRoute(
    onStartupFinished: (StartupLoadedState) -> Unit,
) {
    val startupViewModel = hiltViewModel<StartupViewModel>()

    val state by startupViewModel.state.collectAsState()

    LaunchedEffect(state) {
        (state as? StartupLoadedState)?.let(onStartupFinished)
    }

    when (state) {
        StartupErrorState -> ErrorView()
        else -> Loader()
    }
}