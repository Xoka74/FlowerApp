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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = PlantPickViewModel.ViewModelFactory::class)
class PlantPickViewModel @AssistedInject constructor(
    @Assisted private val plantPickType: PlantPickType,
    private val plantsRepository: PlantRepository,
    private val myPlantsRepository: MyPlantsRepository,
) : BaseViewModel<PlantPickUiState>(PlantPickLoadingState) {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    init {
        subscribeToChanges()
    }

    private fun subscribeToChanges() {
        viewModelScope.launch { searchText.collect { loadPlants() } }
    }

    fun onSearch(newSearchText: String) {
        _searchText.update { newSearchText }
    }


    private fun loadPlants() {
        viewModelScope.launch {
            runCatching {
                updateUiState { PlantPickLoadingState }

                myPlantsRepository.getAll().collect { plants ->
                    updateUiState {
                        PlantPickLoadedState(
                            plants = plants
                                .filter { myPlant ->
                                    myPlant.name.lowercase()
                                        .contains(searchText.value.lowercase())
                                }
                                .map { myPlant ->
                                    myPlant.toPresentationModel()!!
                                })
                    }

                }

            }.onFailure {
                updateUiState { PlantPickLoadingErrorState }
            }
        }
    }

    @AssistedFactory
    interface ViewModelFactory {

        fun create(plantPickType: PlantPickType): PlantPickViewModel
    }
}