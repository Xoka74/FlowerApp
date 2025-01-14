package com.shurdev.settings.viewModel

import com.shurdev.domain.models.settings.Settings

sealed interface SettingsUiState

data object SettingsLoadingState : SettingsUiState

data class SettingsLoadedState(val settings: Settings) : SettingsUiState

data object SettingsErrorState : SettingsUiState