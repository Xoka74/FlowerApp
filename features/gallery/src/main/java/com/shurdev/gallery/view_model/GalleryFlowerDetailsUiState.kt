package com.shurdev.gallery.view_model

import androidx.compose.runtime.Stable
import com.shurdev.domain.models.Flower

@Stable
sealed class GalleryFlowerDetailsUiState

@Stable
data object GalleryFlowerDetailsLoadingState : GalleryFlowerDetailsUiState()

@Stable
data class GalleryFlowerDetailsLoadedState(val flower: Flower) : GalleryFlowerDetailsUiState()

@Stable
data object GalleryFlowerDetailsErrorState : GalleryFlowerDetailsUiState()
