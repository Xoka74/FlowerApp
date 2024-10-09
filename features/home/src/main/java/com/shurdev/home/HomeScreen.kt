package com.shurdev.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.shurdev.domain.models.Flower
import com.shurdev.home.components.FlowerGrid
import com.shurdev.home.view_model.HomeLoadedState
import com.shurdev.home.view_model.HomeLoadingErrorState
import com.shurdev.home.view_model.HomeLoadingState
import com.shurdev.home.view_model.HomeUiState
import com.shurdev.home.view_model.HomeViewModel
import com.shurdev.ui_kit.errors.ErrorView
import com.shurdev.ui_kit.loaders.Loader

@Composable
internal fun HomeRoute(
    onFlowerClick: (Flower) -> Unit,
) {
    val homeViewModel = hiltViewModel<HomeViewModel>()

    val uiState by homeViewModel.uiState.collectAsState()

    HomeScreen(
        uiState = uiState,
        onFlowerClick = onFlowerClick,
    )
}

@Composable
internal fun HomeScreen(
    uiState: HomeUiState,
    onFlowerClick: (Flower) -> Unit,
) {
    when (uiState) {
        is HomeLoadedState -> FlowerGrid(
            items = uiState.flowers,
            onFlowerClick = onFlowerClick,
        )

        HomeLoadingErrorState -> ErrorView()
        HomeLoadingState -> Loader()
    }
}

