package com.shurdev.gallery.screens.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.shurdev.domain.models.Plant
import com.shurdev.gallery.mocks.Plants
import com.shurdev.gallery.screens.details.viewModel.GalleryPlantDetailsErrorState
import com.shurdev.gallery.screens.details.viewModel.GalleryPlantDetailsLoadedState
import com.shurdev.gallery.screens.details.viewModel.GalleryPlantDetailsLoadingState
import com.shurdev.gallery.screens.details.viewModel.GalleryPlantDetailsUiState
import com.shurdev.gallery.screens.details.viewModel.GalleryPlantDetailsViewModel
import com.shurdev.uiKit.actions.FavoriteAction
import com.shurdev.uiKit.buttons.BackButton
import com.shurdev.uiKit.buttons.PrimaryButton
import com.shurdev.uiKit.errors.ErrorView
import com.shurdev.uiKit.loaders.Loader
import com.shurdev.ui_kit.R

@Composable
internal fun GalleryPlantDetailsRoute(
    plantId: Int,
    onPop: () -> Unit,
    // TODO: Refactor
    viewModel: GalleryPlantDetailsViewModel = hiltViewModel<
        GalleryPlantDetailsViewModel,
        GalleryPlantDetailsViewModel.ViewModelFactory,
    > { factory ->
        factory.create(plantId)
    },
) {
    val uiState by viewModel.uiState.collectAsState()

    GalleryPlantDetailsScreen(
        uiState = uiState,
        onPop = onPop,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun GalleryPlantDetailsScreen(
    uiState: GalleryPlantDetailsUiState,
    onPop: () -> Unit,
) {
    Column {
        TopAppBar(
            title = {},
            windowInsets = WindowInsets.Companion.ime,
            navigationIcon = { BackButton(onPop) },
            actions = {
                // TODO: Implement me
                FavoriteAction(
                    isActive = false,
                    onClick = {},
                )
            },
        )
        Box(
            Modifier
                .padding(horizontal = 16.dp),
        ) {
            when (uiState) {
                GalleryPlantDetailsErrorState -> ErrorView()
                GalleryPlantDetailsLoadingState -> Loader()
                is GalleryPlantDetailsLoadedState -> GalleryPlantDetailsContent(uiState.plant)
            }
        }
    }
}

@Composable
internal fun GalleryPlantDetailsContent(plant: Plant) {
    val typography = MaterialTheme.typography

    Column {
        Column(
            Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState()),
        ) {
            AsyncImage(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .fillMaxWidth()
                    .aspectRatio(1F)
                    .height(400.dp),
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(plant.imageLink)
                    .placeholder(com.shurdev.gallery.R.drawable.flower_placeholder_1)
                    .build(),
                contentDescription = "Your Plant",
                contentScale = ContentScale.Crop,
            )

            Spacer(Modifier.height(12.dp))

            Text(
                text = plant.name,
                style = typography.titleLarge,
            )

            Spacer(Modifier.height(12.dp))

            Text(
                text = plant.description,
            )
        }

        val addPlant = stringResource(R.string.add_plant)

        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = addPlant,
            onClick = {
                // TODO: Navigate to add plant
            },
        )
    }
}

@Preview
@Composable
internal fun GalleryPlantDetailsScreenPreview() {
    GalleryPlantDetailsScreen(
        onPop = {},
        uiState = GalleryPlantDetailsLoadedState(
            plant = Plants[0],
        ),
    )
}
