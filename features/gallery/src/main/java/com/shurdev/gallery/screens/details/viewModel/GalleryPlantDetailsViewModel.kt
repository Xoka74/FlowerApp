package com.shurdev.gallery.screens.details.viewModel

import androidx.lifecycle.viewModelScope
import com.shurdev.domain.models.plant.PlantId
import com.shurdev.domain.repositories.PlantRepository
import com.shurdev.ui_kit.viewModel.base.BaseViewModel
import com.shurdev.utils.runSuspendCatching
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = GalleryPlantDetailsViewModel.ViewModelFactory::class)
class GalleryPlantDetailsViewModel @AssistedInject constructor(
    @Assisted private val plantId: Int,
    private val plantRepository: PlantRepository,
) : BaseViewModel<GalleryPlantDetailsUiState>(GalleryPlantDetailsLoadingState) {

    init {
        getPlant(plantId)
    }

    private fun getPlant(id: Int) {
        updateUiState { GalleryPlantDetailsLoadingState }

        viewModelScope.launch {
            runSuspendCatching {
                val plant = plantRepository.getPlantById(id)

                if (plant != null) {
                    updateUiState { GalleryPlantDetailsLoadedState(plant = plant) }
                } else {
                    updateUiState { GalleryPlantDetailsErrorState }
                }
            }.onFailure {
                updateUiState { GalleryPlantDetailsErrorState }
            }
        }
    }

    @AssistedFactory
    interface ViewModelFactory {

        fun create(plantId: PlantId): GalleryPlantDetailsViewModel
    }
}