package com.shurdev.my_plants.screens.myPlants.viewModel

import androidx.lifecycle.viewModelScope
import com.shurdev.domain.repositories.MyPlantsRepository
import com.shurdev.ui_kit.viewModel.base.BaseViewModel
import com.shurdev.utils.runSuspendCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPlantsViewModel @Inject constructor(
    private val myPlantsRepository: MyPlantsRepository,
) : BaseViewModel<MyPlantsUiState>(MyPlantsLoadingState) {
    init {
        viewModelScope.launch {
            myPlantsRepository.getAll().collect { plants ->
                runSuspendCatching {
                    updateUiState { MyPlantsLoadedState(plants) }
                }.onFailure {
                    updateUiState { MyPlantsLoadingErrorState }
                }
            }
        }
    }
}