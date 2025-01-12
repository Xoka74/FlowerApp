package com.shurdev.settings.viewModel

import androidx.lifecycle.viewModelScope
import com.shurdev.domain.models.settings.ThemeType
import com.shurdev.domain.repositories.SettingsRepository
import com.shurdev.ui_kit.viewModel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
) : BaseViewModel<SettingsUiState>(SettingsLoadingState) {

    init {
        subscribeToSettings()
    }

    private fun subscribeToSettings() {
        viewModelScope.launch {
            settingsRepository.settings.catch {
                updateUiState { SettingsErrorState }
            }.collect { settings ->
                updateUiState { SettingsLoadedState(settings) }
            }
        }
    }

    fun updateTheme(value: ThemeType) {
        viewModelScope.launch {
            settingsRepository.updateSettings {
                it.copy(themeType = value)
            }
        }
    }
}