package com.shurdev.recommended_plants.screens.recommendedPlants.viewModel

import androidx.lifecycle.viewModelScope
import com.shurdev.domain.repositories.SurveyRepository
import com.shurdev.ui_kit.viewModel.base.BaseViewModel
import com.shurdev.utils.runSuspendCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecommendedPlantsViewModel @Inject constructor(
    private val surveyRepository: SurveyRepository,
) : BaseViewModel<RecommendedPlantsUiState>(RecommendedPlantsLoadingState) {

    init {
        getRecommendedPlants()
    }

    private fun getRecommendedPlants() {
        updateUiState { RecommendedPlantsLoadingState }

        viewModelScope.launch {
            runSuspendCatching {
                val plants = surveyRepository.getRecommendedPlants()
                updateUiState { RecommendedPlantsLoadedState(plants = plants) }
            }.onFailure {
                updateUiState { RecommendedPlantsLoadingErrorState }
            }
        }
    }
}