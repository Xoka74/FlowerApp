package com.shurdev.my_plants.screens.details.viewModel

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

@HiltViewModel(assistedFactory = MyPlantDetailsViewModel.ViewModelFactory::class)
class MyPlantDetailsViewModel @AssistedInject constructor(
    @Assisted private val plantId: Int,
    private val plantRepository: PlantRepository,
) : ViewModel() {

    private var _uiState =
        MutableStateFlow<MyPlantDetailsUiState>(MyPlantDetailsLoadingState)

    val uiState = _uiState.asStateFlow()

    init {
        getPlant(plantId)
    }

    private fun getPlant(id: Int) {
        _uiState.value = MyPlantDetailsLoadingState

        viewModelScope.launch {
            runSuspendCatching {
                val plant = plantRepository.getPlantById(id)

                if (plant != null) {
                    _uiState.value = MyPlantDetailsLoadedState(plant = plant)
                } else {
                    _uiState.value = MyPlantDetailsErrorState
                }
            }.onFailure {
                _uiState.value = MyPlantDetailsErrorState
            }
        }
    }

    @AssistedFactory
    interface ViewModelFactory {

        fun create(plantId: PlantId): MyPlantDetailsViewModel
    }
}