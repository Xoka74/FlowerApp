package com.shurdev.flowerapp.presentation.screens.startup.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shurdev.domain.repositories.StartupRepository
import com.shurdev.utils.runSuspendCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartupViewModel @Inject constructor(
    private val startupRepository: StartupRepository,
) : ViewModel() {

    private val _state = MutableStateFlow<StartupState>(StartupLoadingState)

    val state = _state.asStateFlow()

    init {
        loadSettings()
        setFirstRun()
    }

    private fun setFirstRun() {
        viewModelScope.launch {
            startupRepository.setFirstRun()
        }
    }

    private fun loadSettings() {
        viewModelScope.launch {
            val isFirstRun = startupRepository.isFirstRun()

            runSuspendCatching {
                _state.update {
                    StartupLoadedState(
                        isFirstRun = isFirstRun
                    )
                }
            }.onFailure {
                _state.update { StartupErrorState }
            }
        }
    }
}