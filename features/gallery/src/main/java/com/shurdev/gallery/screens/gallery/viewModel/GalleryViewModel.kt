package com.shurdev.gallery.screens.gallery.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shurdev.domain.models.PlantFilters
import com.shurdev.domain.repositories.PlantRepository
import com.shurdev.utils.runSuspendCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val plantRepository: PlantRepository,
) : ViewModel() {

    private var _uiState = MutableStateFlow<GalleryUiState>(GalleryLoadingState)
    val uiState: StateFlow<GalleryUiState> = _uiState.asStateFlow()

    init {
        getAllPlants()
    }

    fun onSearchTextChange(text: String) {
        getPlantsBySearchQuery(text)
    }

    private fun getAllPlants() {
        _uiState.value = GalleryLoadingState
        viewModelScope.launch {
            runCatching {
                val plants = plantRepository.getPlants()
                _uiState.update { GalleryLoadedState(plants = plants) }
            }.onFailure {
                _uiState.update { GalleryLoadingErrorState }
            }
        }
    }

    private fun getPlantsBySearchQuery(query: String) {
        _uiState.value = GalleryLoadingState

        viewModelScope.launch {
            runSuspendCatching {
                val plants = plantRepository.getPlants(
                    filters = PlantFilters(
                        name = query,
                        description = query,
                    )
                )

                _uiState.update { GalleryLoadedState(plants = plants) }
            }.onFailure {
                _uiState.update { GalleryLoadingErrorState }
            }
        }
    }
}