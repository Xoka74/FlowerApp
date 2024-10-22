package com.shurdev.gallery.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shurdev.domain.models.Flower
import com.shurdev.domain.repositories.FlowerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    flowersRepository: FlowerRepository,
) : ViewModel() {

    val uiState: StateFlow<GalleryUiState> = flowersRepository.flowers
        .map<List<Flower>, GalleryUiState> { GalleryLoadedState(it) }
        .catch { emit(GalleryLoadingErrorState) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = GalleryLoadingState
        )
}