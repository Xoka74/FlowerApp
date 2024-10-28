package com.shurdev.gallery

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.shurdev.gallery.mocks.FLOWERS
import com.shurdev.gallery.view_model.GalleryFlowerDetailsErrorState
import com.shurdev.gallery.view_model.GalleryFlowerDetailsLoadedState
import com.shurdev.gallery.view_model.GalleryFlowerDetailsLoadingState
import com.shurdev.gallery.view_model.GalleryFlowerDetailsUiState
import com.shurdev.gallery.view_model.GalleryFlowerDetailsViewModel
import com.shurdev.ui_kit.components.Center

@Composable
internal fun GalleryFlowerDetailsRoute(
    flowerId: Int,
) {
    val viewModel =
        hiltViewModel<GalleryFlowerDetailsViewModel, GalleryFlowerDetailsViewModel.ViewModelFactory> { factory ->
            factory.create(flowerId)
        }

    val uiState by viewModel.uiState.collectAsState()

    GalleryFlowerDetailsScreen(
        uiState = uiState
    )
}

@Composable
internal fun GalleryFlowerDetailsScreen(
    uiState: GalleryFlowerDetailsUiState
) {

    when (uiState) {
        is GalleryFlowerDetailsLoadedState -> {
            val flower = uiState.flower

            Text(
                text = "Gallery Flower details"
            )

            Center {
                Column {

                    AsyncImage(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp)),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(flower.imageLink)
                            .placeholder(R.drawable.flower_placeholder_1)
                            .build(),
                        contentDescription = "Your Flower",
                        contentScale = ContentScale.Crop
                    )

                    Text(
                        text = flower.id.toString()
                    )

                    Text(
                        text = flower.name
                    )

                    Text(
                        text = flower.description
                    )


                }
            }
        }

        is GalleryFlowerDetailsErrorState -> {
            // TODO
        }

        is GalleryFlowerDetailsLoadingState -> {
            // TODO
        }
    }
}

@Preview
@Composable
internal fun GalleryFlowerDetailsScreenPreview() {
    GalleryFlowerDetailsScreen(
        uiState = GalleryFlowerDetailsLoadedState(
            flower = FLOWERS[0]
        )
    )
}