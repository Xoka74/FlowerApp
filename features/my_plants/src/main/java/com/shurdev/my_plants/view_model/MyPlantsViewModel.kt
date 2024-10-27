package com.shurdev.my_plants.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shurdev.domain.repositories.FlowerRepository
import com.shurdev.utils.runSuspendCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPlantsViewModel @Inject constructor(
    private val flowersRepository: FlowerRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow<MyPlantsUiState>(MyPlantsLoadingState)
    val uiState = _uiState.asStateFlow()

    init {
        refresh()
    }

    private fun refresh() {
        _uiState.update { MyPlantsLoadingState }

        viewModelScope.launch {
            runSuspendCatching {

                val flowers = flowersRepository.getFlowers()
                _uiState.update { MyPlantsLoadedState(flowers) }
            }.onFailure {
                _uiState.update { MyPlantsLoadingErrorState }
            }
        }
    }
}