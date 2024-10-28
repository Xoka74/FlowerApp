package com.shurdev.my_plants.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shurdev.domain.repositories.FlowerRepository
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
    private val flowerRepository: FlowerRepository
) : ViewModel() {

    private var _uiState =
        MutableStateFlow<MyPlantDetailsUiState>(MyPlantDetailsLoadingState)

    val uiState = _uiState.asStateFlow()

    init {
        getFlower(plantId)
    }

    private fun getFlower(id: Int) {
        _uiState.value = MyPlantDetailsLoadingState

        viewModelScope.launch {
            runSuspendCatching {
                val flower = flowerRepository.getFlowerById(id)

                if (flower != null) {
                    _uiState.value = MyPlantDetailsLoadedState(flower = flower)
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

        fun create(flowerId: Int): MyPlantDetailsViewModel
    }
}