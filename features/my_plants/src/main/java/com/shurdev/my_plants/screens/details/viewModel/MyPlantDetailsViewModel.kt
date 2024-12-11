package com.shurdev.my_plants.screens.details.viewModel

import androidx.lifecycle.viewModelScope
import com.shurdev.domain.models.plant.PlantId
import com.shurdev.domain.repositories.MyPlantsRepository
import com.shurdev.domain.usecases.DeleteMyPlantUseCase
import com.shurdev.ui_kit.viewModel.base.BaseViewModel
import com.shurdev.utils.runSuspendCatching
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = MyPlantDetailsViewModel.ViewModelFactory::class)
class MyPlantDetailsViewModel @AssistedInject constructor(
    @Assisted private val plantId: Int,
    private val myPlantsRepository: MyPlantsRepository,
    private val deleteMyPlantUseCase: DeleteMyPlantUseCase,
) : BaseViewModel<MyPlantDetailsUiState>(MyPlantDetailsLoadingState) {

    init {
        getPlant(plantId)
    }

    private fun getPlant(id: Int) {
        updateUiState { MyPlantDetailsLoadingState }

        viewModelScope.launch {
            runSuspendCatching {
                val plant = myPlantsRepository.getById(id)

                updateUiState { MyPlantDetailsLoadedState(plant = plant) }
            }.onFailure {
                updateUiState { MyPlantDetailsErrorState }
            }
        }
    }

    fun deletePlant() {
        viewModelScope.launch {
            runSuspendCatching {
                val state = uiState.value
                if (state !is MyPlantDetailsLoadedState){
                    return@launch
                }

                deleteMyPlantUseCase(
                    id = plantId,
                    name = state.plant.name
                )

                updateUiState { MyPlantDeletedState }
            }.onFailure {
                updateUiState { MyPlantDetailsErrorState }
            }
        }
    }

    @AssistedFactory
    interface ViewModelFactory {

        fun create(plantId: PlantId): MyPlantDetailsViewModel
    }
}