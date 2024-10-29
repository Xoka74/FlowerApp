package com.shurdev.gallery.view_model

import androidx.compose.runtime.Stable
import com.shurdev.domain.models.Plant

@Stable
sealed class GalleryUiState

@Stable
data object GalleryLoadingState : GalleryUiState()

@Stable
data class GalleryLoadedState(val plants: List<Plant>) : GalleryUiState()

@Stable
data object GalleryLoadingErrorState : GalleryUiState()
