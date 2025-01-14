package com.shurdev.trade.screens.plantPick.viewModel

import androidx.lifecycle.viewModelScope
import com.shurdev.domain.repositories.MyPlantsRepository
import com.shurdev.domain.repositories.PlantRepository
import com.shurdev.trade.mappers.toPresentationModel
import com.shurdev.trade.screens.plantPick.PlantPickType
import com.shurdev.ui_kit.viewModel.base.BaseViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = PlantPickViewModel.ViewModelFactory::class)
class PlantPickViewModel @AssistedInject constructor(
    @Assisted private val plantPickType: PlantPickType,
    private val plantsRepository: PlantRepository,
    private val myPlantsRepository: MyPlantsRepository,
) : BaseViewModel<PlantPickUiState>(PlantPickLoadingState) {

    init {
        viewModelScope.launch {
            loadPlants()
        }
    }

    fun onSearchTextText(newSearchText: String) {

    }

    private suspend fun loadPlants() {
        updateUiState { PlantPickLoadingState }

        runCatching {

            if (plantPickType == PlantPickType.PlantToGive) {
                myPlantsRepository.getAll().collect { plants ->
                    updateUiState {
                        PlantPickLoadedState(plants = plants.map { myPlant ->
                            myPlant.toPresentationModel()!!
                        })
                    }

                }
            }

        }.onFailure {
            updateUiState { PlantPickLoadingErrorState }
        }
    }

    @AssistedFactory
    interface ViewModelFactory {

        fun create(plantPickType: PlantPickType): PlantPickViewModel
    }
}