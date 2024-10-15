package com.shurdev.home

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shurdev.domain.models.Flower
import com.shurdev.home.components.AppSearchField
import com.shurdev.home.view_model.HomeLoadedState
import com.shurdev.home.view_model.HomeLoadingErrorState
import com.shurdev.home.view_model.HomeLoadingState
import com.shurdev.home.view_model.HomeUiState
import com.shurdev.home.view_model.HomeViewModel
import com.shurdev.home.your_flowers.YourFlowers
import com.shurdev.ui_kit.errors.ErrorView
import com.shurdev.ui_kit.loaders.Loader

@Composable
internal fun HomeRoute(
    onFlowerClick: (Flower) -> Unit,
) {
    val homeViewModel = hiltViewModel<HomeViewModel>()

    val uiState by homeViewModel.uiState.collectAsState()
    val searchText by homeViewModel.searchText.collectAsState()
    val isSearching by homeViewModel.isSearching.collectAsState()

    HomeScreen(
        uiState = uiState,
        onFlowerClick = onFlowerClick,
        searchText = searchText,
        onSearchTextChange = homeViewModel::onSearchTextChange,
    )
}

@Composable
internal fun HomeScreen(
    uiState: HomeUiState,
    onFlowerClick: (Flower) -> Unit,
    searchText: String,
    onSearchTextChange: (String) -> Unit,
) {
    when (uiState) {
        is HomeLoadedState -> {

            val context = LocalContext.current

            Scaffold(
                containerColor = AppBackgroundColor,
            ) { padding ->
                Column {
                    AppSearchField(
                        modifier = Modifier
                            .padding(vertical = 12.dp)
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth(),
                        hint = "Поиск цветов",
                        value = searchText,
                        onValueChange = { query ->
                            onSearchTextChange(query)
                        }
                    )

                    Text(
                        modifier = Modifier
                            .padding(vertical = 12.dp)
                            .padding(horizontal = 16.dp),
                        text = "Ваши растения",
                        color = FlowerCardContentColor,
                        fontSize = 24.sp,
                        style = TextStyle(fontWeight = FontWeight.Bold)
                    )

                    YourFlowers(
                        flowers = uiState.flowers
                            .mapIndexed { index, flower ->
                                val imageUri = LocalContext.current.resourceUri(
                                    if (index % 2 == 0) R.drawable.flower_placeholder_1 else R.drawable.flower_placeholder_2
                                )

                                flower.copy(
                                    imageLink = imageUri.toString()
                                )
                            },
                        onFlowerClick = {
                            Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
                            onFlowerClick(it)
                        }
                    )
                }
            }
        }

        HomeLoadingErrorState -> ErrorView()
        HomeLoadingState -> Loader()
    }
}

// TODO move to extensions

val String.color
    get() = Color(android.graphics.Color.parseColor(this))

fun Context.resourceUri(resourceId: Int): Uri = with(resources) {
    Uri.Builder()
        .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
        .authority(getResourcePackageName(resourceId))
        .appendPath(getResourceTypeName(resourceId))
        .appendPath(getResourceEntryName(resourceId))
        .build()
}