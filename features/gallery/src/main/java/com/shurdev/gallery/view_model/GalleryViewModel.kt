package com.shurdev.gallery.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shurdev.domain.models.FlowerFilters
import com.shurdev.domain.repositories.FlowerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val flowersRepository: FlowerRepository,
) : ViewModel() {

    private var _uiState = MutableStateFlow<GalleryUiState>(GalleryLoadingState)
    val uiState: StateFlow<GalleryUiState> = _uiState.asStateFlow()

    init {
        getAllFlowers()
    }

    fun onSearchTextChange(text: String) {
        getFlowersBySearchQuery(text)
    }

    private fun getAllFlowers() {
        _uiState.value = GalleryLoadingState
        viewModelScope.launch {
            runCatching {
                val flowers = flowersRepository.getFlowers()
                _uiState.update { GalleryLoadedState(flowers = flowers) }
            }.onFailure {
                _uiState.update { GalleryLoadingErrorState }
            }
        }
    }

    private fun getFlowersBySearchQuery(query: String) {
        _uiState.value = GalleryLoadingState
        viewModelScope.launch {
            runCatching {
                val flowers = flowersRepository.getFlowersByFilters(
                    filters = FlowerFilters(
                        name = query,
                        description = query
                    )
                )

                _uiState.update { GalleryLoadedState(flowers = flowers) }
            }.onFailure {
                _uiState.update { GalleryLoadingErrorState }
            }
        }
    }
}