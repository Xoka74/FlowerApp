package com.shurdev.gallery.screens.gallery.viewModel

import androidx.lifecycle.viewModelScope
import com.shurdev.domain.models.plant.PlantFilters
import com.shurdev.domain.repositories.PlantRepository
import com.shurdev.ui_kit.viewModel.base.BaseViewModel
import com.shurdev.utils.runSuspendCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val plantRepository: PlantRepository,
) : BaseViewModel<GalleryUiState>(GalleryLoadingState) {
    init {
        getAllPlants()
    }

    fun onSearchTextChange(text: String) {
        getPlantsBySearchQuery(text)
    }

    private fun getAllPlants() {
        updateUiState { GalleryLoadingState }
        viewModelScope.launch {
            runCatching {
                val plants = plantRepository.getPlants()
                updateUiState { GalleryLoadedState(plants = plants) }
            }.onFailure {
                updateUiState { GalleryLoadingErrorState }
            }
        }
    }

    private fun getPlantsBySearchQuery(query: String) {
        updateUiState { GalleryLoadingState }

        viewModelScope.launch {
            runSuspendCatching {
                val plants = plantRepository.getPlants(
                    filters = PlantFilters(
                        name = query,
                        description = query,
                    )
                )

                updateUiState { GalleryLoadedState(plants = plants) }
            }.onFailure {
                updateUiState { GalleryLoadingErrorState }
            }
        }
    }
}