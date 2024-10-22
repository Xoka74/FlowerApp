package com.shurdev.gallery

import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.shurdev.domain.models.Flower
import com.shurdev.gallery.view_model.GalleryLoadedState
import com.shurdev.gallery.view_model.GalleryLoadingErrorState
import com.shurdev.gallery.view_model.GalleryLoadingState
import com.shurdev.gallery.view_model.GalleryUiState
import com.shurdev.gallery.view_model.GalleryViewModel
import com.shurdev.ui_kit.errors.ErrorView
import com.shurdev.ui_kit.loaders.Loader

@Composable
internal fun GalleryRoute(
    onFlowerClick: (Flower) -> Unit
){
    val homeViewModel = hiltViewModel<GalleryViewModel>()

    val uiState by homeViewModel.uiState.collectAsState()

    GalleryScreen(
        uiState = uiState,
        onFlowerClick = onFlowerClick,
    )
}

@Composable
internal fun GalleryScreen(
    uiState: GalleryUiState,
    onFlowerClick: (Flower) -> Unit
){
    when (uiState) {
        is GalleryLoadedState -> {
            Column {
                Row{

                }

                // Search TODO

                Text(text = "Популярные растения")

                LazyColumn {
                    items(uiState.flowers){

                    }
                }
            }
        }

        GalleryLoadingErrorState -> ErrorView()
        GalleryLoadingState -> Loader()
    }
}
