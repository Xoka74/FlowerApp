package com.shurdev.gallery

import android.widget.Toast
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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

@Composable
internal fun GalleryRoute(
    onFlowerClick: (Flower) -> Unit,
    onCategoryClick: (String) -> Unit
) {
    val homeViewModel = hiltViewModel<GalleryViewModel>()

    val uiState by homeViewModel.uiState.collectAsState()
    val searchText by homeViewModel.searchText.collectAsState()

    GalleryScreen(
        uiState = uiState,
        onFlowerClick = onFlowerClick,
        onCategoryClick = onCategoryClick,
        searchText = searchText,
        onSearchTextChange = homeViewModel::onSearchTextChange
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
    when (uiState) {
        is GalleryLoadedState -> {
            val context = LocalContext.current

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
                        Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show() // TODO snackbar
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
                        Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
                        onFlowerClick(it)
                    }
                )
            }
        }

        GalleryLoadingErrorState -> ErrorView()
        GalleryLoadingState -> Loader()
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
