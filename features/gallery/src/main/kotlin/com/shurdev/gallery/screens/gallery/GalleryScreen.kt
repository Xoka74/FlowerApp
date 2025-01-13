package com.shurdev.gallery.screens.gallery

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shurdev.domain.models.plant.Plant
import com.shurdev.gallery.R
import com.shurdev.gallery.components.PlantsList
import com.shurdev.gallery.mocks.Plants
import com.shurdev.gallery.screens.gallery.viewModel.GalleryLoadedState
import com.shurdev.gallery.screens.gallery.viewModel.GalleryLoadingErrorState
import com.shurdev.gallery.screens.gallery.viewModel.GalleryLoadingState
import com.shurdev.gallery.screens.gallery.viewModel.GalleryUiState
import com.shurdev.gallery.screens.gallery.viewModel.GalleryViewModel
import com.shurdev.ui_kit.actions.SettingsAction
import com.shurdev.ui_kit.errors.ErrorView
import com.shurdev.ui_kit.fields.SearchField
import com.shurdev.ui_kit.layouts.DefaultScreenLayout
import com.shurdev.ui_kit.loaders.Loader

@Composable
internal fun GalleryRoute(
    onPlantClick: (Plant) -> Unit,
) {
    val galleryViewModel = hiltViewModel<GalleryViewModel>()

    val uiState by galleryViewModel.uiState.collectAsState()

    GalleryScreen(
        uiState = uiState,
        onPlantClick = onPlantClick,
        onCategoryClick = {},
        onSearchTextChange = galleryViewModel::onSearchTextChange
    )
}

@Composable
internal fun GalleryScreen(
    uiState: GalleryUiState,
    onPlantClick: (Plant) -> Unit,
    onCategoryClick: (String) -> Unit,
    onSearchTextChange: (String) -> Unit,
) {
    when (uiState) {
        GalleryLoadingErrorState -> ErrorView()
        GalleryLoadingState -> Loader()
        is GalleryLoadedState -> GalleryScreenContent(
            plants = uiState.plants,
            onPlantClick = onPlantClick,
            onCategoryClick = onCategoryClick,
            onSearchTextChange = onSearchTextChange,
        )
    }
}

@Composable
internal fun GalleryScreenContent(
    plants: List<Plant>,
    onPlantClick: (Plant) -> Unit,
    onCategoryClick: (String) -> Unit,
    onSearchTextChange: (String) -> Unit,
) {

    DefaultScreenLayout(
        title = stringResource(R.string.search),
    ) {
        Column {
            SearchField(
                modifier = Modifier.padding(horizontal = 16.dp),
                hint = stringResource(R.string.search_hint),
                onSearchTextChange = onSearchTextChange,
                debounceTimeMillis = 300L,
            )

            Spacer(Modifier.height(20.dp))

            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = stringResource(R.string.popular_plants),
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal
            )

            PlantsList(
                plants = plants,
                onPlantClick = onPlantClick
            )
        }
    }
}

@Preview
@Composable
internal fun GalleryScreenPreview() {
    GalleryScreen(
        uiState = GalleryLoadedState(
            plants = Plants
        ),
        onPlantClick = {},
        onCategoryClick = {},
        onSearchTextChange = {},
    )
}