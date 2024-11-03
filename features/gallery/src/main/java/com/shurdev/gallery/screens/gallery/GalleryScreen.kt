package com.shurdev.gallery.screens.gallery

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
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
import com.shurdev.domain.models.Plant
import com.shurdev.gallery.R
import com.shurdev.gallery.components.PlantCategoriesList
import com.shurdev.gallery.components.PlantsList
import com.shurdev.gallery.mocks.CATEGORIES
import com.shurdev.gallery.mocks.Plants
import com.shurdev.gallery.screens.gallery.viewModel.GalleryLoadedState
import com.shurdev.gallery.screens.gallery.viewModel.GalleryLoadingErrorState
import com.shurdev.gallery.screens.gallery.viewModel.GalleryLoadingState
import com.shurdev.gallery.screens.gallery.viewModel.GalleryUiState
import com.shurdev.gallery.screens.gallery.viewModel.GalleryViewModel
import com.shurdev.ui_kit.fields.SearchField
import com.shurdev.ui_kit.errors.ErrorView
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

    Scaffold { padding ->
        when (uiState) {
            is GalleryLoadedState -> {
                Column(
                    modifier = Modifier
                ) {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(top = 20.dp)
                            .fillMaxWidth(),
                        text = stringResource(R.string.search),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )

                    SearchField(
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .padding(horizontal = 16.dp),
                        hint = stringResource(R.string.search_hint),
                        onSearchTextChange = onSearchTextChange,
                        debounceTimeMillis = 800L
                    )

                    PlantCategoriesList(
                        categories = CATEGORIES,
                        onCategoryClick = onCategoryClick
                    )

                    Text(
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .padding(horizontal = 16.dp),
                        text = stringResource(R.string.popular_plants),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal
                    )

                    PlantsList(
                        plants = uiState.plants,
                        onPlantClick = onPlantClick
                    )
                }
            }

            GalleryLoadingErrorState -> ErrorView()
            GalleryLoadingState -> Loader()
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