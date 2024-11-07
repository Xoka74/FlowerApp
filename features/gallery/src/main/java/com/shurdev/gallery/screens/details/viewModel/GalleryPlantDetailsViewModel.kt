package com.shurdev.gallery.screens.details.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shurdev.domain.models.PlantId
import com.shurdev.domain.repositories.PlantRepository
import com.shurdev.utils.runSuspendCatching
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = GalleryPlantDetailsViewModel.ViewModelFactory::class)
class GalleryPlantDetailsViewModel @AssistedInject constructor(
    @Assisted private val plantId: Int,
    private val plantRepository: PlantRepository,
) : ViewModel() {
    private var _uiState =
        MutableStateFlow<GalleryPlantDetailsUiState>(GalleryPlantDetailsLoadingState)

    val uiState = _uiState.asStateFlow()

    init {
        getPlant(plantId)
    }

    private fun getPlant(id: Int) {
        _uiState.value = GalleryPlantDetailsLoadingState

        viewModelScope.launch {
            runSuspendCatching {
                val plant = plantRepository.getPlantById(id)

                // TODO: Refactor
                if (plant != null) {
                    _uiState.value = GalleryPlantDetailsLoadedState(plant = plant)
                } else {
                    _uiState.value = GalleryPlantDetailsErrorState
                }
            }.onFailure {
                _uiState.value = GalleryPlantDetailsErrorState
            }
        }
    }

    @AssistedFactory
    interface ViewModelFactory {
        fun create(plantId: PlantId): GalleryPlantDetailsViewModel
    }
}
