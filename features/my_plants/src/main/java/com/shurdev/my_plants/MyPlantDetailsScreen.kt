package com.shurdev.my_plants

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
import com.shurdev.domain.models.Flower
import com.shurdev.my_plants.view_model.MyPlantDetailsErrorState
import com.shurdev.my_plants.view_model.MyPlantDetailsLoadedState
import com.shurdev.my_plants.view_model.MyPlantDetailsLoadingState
import com.shurdev.my_plants.view_model.MyPlantDetailsUiState
import com.shurdev.my_plants.view_model.MyPlantDetailsViewModel
import com.shurdev.ui_kit.components.Center

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
            val flower = uiState.flower

            Text(
                text = "My plant details"
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
            flower = Flower(1, "Тюльпан", "Великолепный", "")
        )
    )
}
