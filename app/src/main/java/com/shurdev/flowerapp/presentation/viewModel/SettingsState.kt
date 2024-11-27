package com.shurdev.flowerapp.presentation.viewModel


sealed interface SettingsState

data object SettingsLoadingState : SettingsState

data class SettingsLoadedState(
    val isFirstRun: Boolean,
) : SettingsState

data object SettingsErrorState : SettingsState