package com.shurdev.gallery

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shurdev.domain.models.Flower
import com.shurdev.gallery.components.FlowerCategoriesList
import com.shurdev.gallery.components.FlowersList
import com.shurdev.gallery.mocks.CATEGORIES
import com.shurdev.gallery.mocks.FLOWERS
import com.shurdev.gallery.viewModel.GalleryLoadedState
import com.shurdev.gallery.viewModel.GalleryLoadingErrorState
import com.shurdev.gallery.viewModel.GalleryLoadingState
import com.shurdev.gallery.viewModel.GalleryUiState
import com.shurdev.gallery.viewModel.GalleryViewModel
import com.shurdev.uiKit.components.SearchField
import com.shurdev.uiKit.errors.ErrorView
import com.shurdev.uiKit.loaders.Loader
import kotlinx.coroutines.launch

@Composable
internal fun GalleryRoute(
    onFlowerClick: (Flower) -> Unit,
    onCategoryClick: (String) -> Unit
) {
    val galleryViewModel = hiltViewModel<GalleryViewModel>()

    val uiState by galleryViewModel.uiState.collectAsState()

    GalleryScreen(
        uiState = uiState,
        onFlowerClick = onFlowerClick,
        onCategoryClick = onCategoryClick,
        onSearchTextChange = galleryViewModel::onSearchTextChange
    )
}

@Composable
internal fun GalleryScreen(
    uiState: GalleryUiState,
    onFlowerClick: (Flower) -> Unit,
    onCategoryClick: (String) -> Unit,
    onSearchTextChange: (String) -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
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

                    FlowerCategoriesList(
                        categories = CATEGORIES,
                        onCategoryClick = {
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = it.toString(),
                                    duration = SnackbarDuration.Short
                                )
                            }

                            onCategoryClick(it)
                        }
                    )

                    Text(
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .padding(horizontal = 16.dp),
                        text = stringResource(R.string.popular_plants),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal
                    )

                    FlowersList(
                        flowers = uiState.flowers,
                        onFlowerClick = {
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = it.toString(),
                                    duration = SnackbarDuration.Short
                                )
                            }

                            onFlowerClick(it)
                        }
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
            flowers = FLOWERS
        ),
        onFlowerClick = {},
        onCategoryClick = {},
        onSearchTextChange = {},
    )
}
