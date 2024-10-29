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
import com.shurdev.gallery.mocks.Plants
import com.shurdev.gallery.view_model.GalleryPlantDetailsErrorState
import com.shurdev.gallery.view_model.GalleryPlantDetailsLoadedState
import com.shurdev.gallery.view_model.GalleryPlantDetailsLoadingState
import com.shurdev.gallery.view_model.GalleryPlantDetailsUiState
import com.shurdev.gallery.view_model.GalleryPlantDetailsViewModel
import com.shurdev.ui_kit.components.Center

@Composable
internal fun GalleryPlantDetailsRoute(
    plantId: Int,
) {
    val viewModel =
        hiltViewModel<GalleryPlantDetailsViewModel, GalleryPlantDetailsViewModel.ViewModelFactory> { factory ->
            factory.create(plantId)
        }

    val uiState by viewModel.uiState.collectAsState()

    GalleryPlantDetailsScreen(
        uiState = uiState
    )
}

@Composable
internal fun GalleryPlantDetailsScreen(
    uiState: GalleryPlantDetailsUiState
) {

    when (uiState) {
        is GalleryPlantDetailsLoadedState -> {
            val plant = uiState.plant

            Text(
                text = "Gallery Plant details"
            )

            Center {
                Column {

                    AsyncImage(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp)),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(plant.imageLink)
                            .placeholder(R.drawable.plant_placeholder_1)
                            .build(),
                        contentDescription = "Your Plant",
                        contentScale = ContentScale.Crop
                    )

                    Text(
                        text = plant.id.toString()
                    )

                    Text(
                        text = plant.name
                    )

                    Text(
                        text = plant.description
                    )


                }
            }
        }

        is GalleryPlantDetailsErrorState -> {
            // TODO
        }

        is GalleryPlantDetailsLoadingState -> {
            // TODO
        }
    }
}

@Preview
@Composable
internal fun GalleryPlantDetailsScreenPreview() {
    GalleryPlantDetailsScreen(
        uiState = GalleryPlantDetailsLoadedState(
            plant = Plants[0]
        )
    )
}