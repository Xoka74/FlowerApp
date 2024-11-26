package com.shurdev.recommended_plants.screens.recommendedPlants.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shurdev.domain.repositories.PlantRepository
import com.shurdev.utils.runSuspendCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecommendedPlantsViewModel @Inject constructor(
    private val plantRepository: PlantRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow<RecommendedPlantsUiState>(RecommendedPlantsLoadingState)
    val uiState = _uiState.asStateFlow()

    init {
        getRecommendedPlants()
    }

    private fun getRecommendedPlants() {
        _uiState.value = RecommendedPlantsLoadingState

        viewModelScope.launch {
            runSuspendCatching {
                val plants = plantRepository.getPlants()
                _uiState.update { RecommendedPlantsLoadedState(plants = plants) }
            }.onFailure {
                _uiState.update { RecommendedPlantsLoadingErrorState }
            }
        }
    }
}