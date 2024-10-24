package com.shurdev.gallery

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.shurdev.gallery.view_model.GalleryLoadedState
import com.shurdev.gallery.view_model.GalleryLoadingErrorState
import com.shurdev.gallery.view_model.GalleryLoadingState
import com.shurdev.gallery.view_model.GalleryUiState
import com.shurdev.gallery.view_model.GalleryViewModel
import com.shurdev.ui_kit.errors.ErrorView
import com.shurdev.ui_kit.loaders.Loader
import kotlinx.coroutines.launch

@Composable
internal fun GalleryRoute(
    onFlowerClick: (Flower) -> Unit,
    onCategoryClick: (String) -> Unit
) {
    val galleryViewModel = hiltViewModel<GalleryViewModel>()

    val uiState by galleryViewModel.uiState.collectAsState()
    val searchText by galleryViewModel.searchText.collectAsState()

    GalleryScreen(
        uiState = uiState,
        onFlowerClick = onFlowerClick,
        onCategoryClick = onCategoryClick,
        searchText = searchText,
        onSearchTextChange = galleryViewModel::onSearchTextChange
    )
}

@Composable
internal fun GalleryScreen(
    uiState: GalleryUiState,
    onFlowerClick: (Flower) -> Unit,
    onCategoryClick: (String) -> Unit,
    searchText: String,
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
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(top = 20.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            modifier = Modifier,
                            text = stringResource(R.string.search),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(top = 10.dp)
                            .padding(horizontal = 16.dp)
                    ) {
                        TextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.LightGray, RoundedCornerShape(16.dp)),
                            value = searchText,
                            onValueChange = onSearchTextChange,
                            placeholder = {
                                Text(stringResource(R.string.search_hint))
                            },
                            leadingIcon = {
                                Icon(Icons.Filled.Search, contentDescription = "Search Icon")
                            },
                            colors = TextFieldDefaults.colors(
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent
                            ),
                            shape = RoundedCornerShape(12.dp),
                        )
                    }

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
        searchText = ""
    )
}
