package com.shurdev.flowerapp.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shurdev.domain.repositories.LocalSettingsRepository
import com.shurdev.utils.runSuspendCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val localSettingsRepository: LocalSettingsRepository,
) : ViewModel() {

    private val _state = MutableStateFlow<SettingsState>(SettingsLoadingState)

    val state = _state.asStateFlow()

    init {
        loadSettings()
        setFirstRun()
    }

    private fun setFirstRun() {
        viewModelScope.launch {
            localSettingsRepository.setFirstRun()
        }
    }

    private fun loadSettings() {
        viewModelScope.launch {
            val isFirstRun = localSettingsRepository.isFirstRun()

            runSuspendCatching {
                _state.update {
                    SettingsLoadedState(
                        isFirstRun = isFirstRun
                    )
                }
            }.onFailure {
                _state.update { SettingsErrorState }
            }
        }
    }
}