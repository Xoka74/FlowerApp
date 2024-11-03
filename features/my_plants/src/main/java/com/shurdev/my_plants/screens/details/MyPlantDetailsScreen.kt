package com.shurdev.my_plants.screens.details

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
import com.shurdev.domain.models.Plant
import com.shurdev.my_plants.R
import com.shurdev.my_plants.screens.details.viewModel.MyPlantDetailsErrorState
import com.shurdev.my_plants.screens.details.viewModel.MyPlantDetailsLoadedState
import com.shurdev.my_plants.screens.details.viewModel.MyPlantDetailsLoadingState
import com.shurdev.my_plants.screens.details.viewModel.MyPlantDetailsUiState
import com.shurdev.my_plants.screens.details.viewModel.MyPlantDetailsViewModel
import com.shurdev.ui_kit.layouts.Center

@Composable
internal fun MyPlantDetailsRoute(
    plantId: Int,
) {
    val viewModel =
        hiltViewModel<MyPlantDetailsViewModel, MyPlantDetailsViewModel.ViewModelFactory> { factory ->
            factory.create(plantId)
        }

    val uiState by viewModel.uiState.collectAsState()

    MyPlantDetailsScreen(
        uiState = uiState,
    )
}

@Composable
internal fun MyPlantDetailsScreen(
    uiState: MyPlantDetailsUiState,
) {

    when (uiState) {
        is MyPlantDetailsLoadedState -> {
            val plant = uiState.plant

            Text(
                text = "My plant details"
            )

            Center {
                Column {

                    AsyncImage(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp)),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(plant.imageLink)
                            .placeholder(R.drawable.flower_placeholder_1)
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

        is MyPlantDetailsLoadingState -> {
            // TODO
        }

        is MyPlantDetailsErrorState -> {
            // TODO
        }
    }
}

@Preview
@Composable
internal fun MyPlantDetailsScreenPreview() {
    MyPlantDetailsScreen(
        uiState = MyPlantDetailsLoadedState(
            plant = Plant(1, "Тюльпан", "Великолепный", "")
        )
    )
}
