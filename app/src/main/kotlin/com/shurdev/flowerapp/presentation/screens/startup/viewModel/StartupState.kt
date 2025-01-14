package com.shurdev.flowerapp.presentation.screens.startup.viewModel


sealed interface StartupState

data object StartupLoadingState : StartupState

data class StartupLoadedState(
    val isFirstRun: Boolean,
) : StartupState

data object StartupErrorState : StartupState