package com.shurdev.gallery.screens.details.viewModel

import androidx.compose.runtime.Stable
import com.shurdev.domain.models.Plant

@Stable
sealed class GalleryPlantDetailsUiState

@Stable
data object GalleryPlantDetailsLoadingState : GalleryPlantDetailsUiState()

@Stable
data class GalleryPlantDetailsLoadedState(
    val plant: Plant,
) : GalleryPlantDetailsUiState()

@Stable
data object GalleryPlantDetailsErrorState : GalleryPlantDetailsUiState()
