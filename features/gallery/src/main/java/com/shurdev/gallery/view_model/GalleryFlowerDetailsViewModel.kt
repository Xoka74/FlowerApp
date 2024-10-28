package com.shurdev.gallery.view_model

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

@HiltViewModel(assistedFactory = GalleryFlowerDetailsViewModel.ViewModelFactory::class)
class GalleryFlowerDetailsViewModel @AssistedInject constructor(
    @Assisted private val flowerId: Int,
    private val flowerRepository: FlowerRepository
) : ViewModel() {

    private var _uiState =
        MutableStateFlow<GalleryFlowerDetailsUiState>(GalleryFlowerDetailsLoadingState)

    val uiState = _uiState.asStateFlow()

    init {
        getFlower(flowerId)
    }

    private fun getFlower(id: Int) {
        _uiState.value = GalleryFlowerDetailsLoadingState

        viewModelScope.launch {
            runSuspendCatching {
                val flower = flowerRepository.getFlowerById(id)

                if (flower != null) {
                    _uiState.value = GalleryFlowerDetailsLoadedState(flower = flower)
                } else {
                    _uiState.value = GalleryFlowerDetailsErrorState
                }
            }.onFailure {
                _uiState.value = GalleryFlowerDetailsErrorState
            }
        }
    }

    @AssistedFactory
    interface ViewModelFactory {

        fun create(flowerId: Int): GalleryFlowerDetailsViewModel
    }
}