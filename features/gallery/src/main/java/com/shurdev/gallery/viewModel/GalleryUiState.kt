package com.shurdev.gallery.viewModel

import androidx.compose.runtime.Stable
import com.shurdev.domain.models.Flower

@Stable
sealed class GalleryUiState

@Stable
data object GalleryLoadingState : GalleryUiState()

@Stable
data class GalleryLoadedState(val flowers: List<Flower>) : GalleryUiState()

@Stable
data object GalleryLoadingErrorState : GalleryUiState()
