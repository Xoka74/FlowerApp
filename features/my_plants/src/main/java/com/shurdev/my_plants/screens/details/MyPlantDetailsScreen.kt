package com.shurdev.my_plants.screens.details

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shurdev.domain.models.myPlant.MyPlant
import com.shurdev.my_plants.screens.details.composables.MyPlantDetailsContent
import com.shurdev.my_plants.screens.details.viewModel.MyPlantDeletedState
import com.shurdev.my_plants.screens.details.viewModel.MyPlantDetailsErrorState
import com.shurdev.my_plants.screens.details.viewModel.MyPlantDetailsLoadedState
import com.shurdev.my_plants.screens.details.viewModel.MyPlantDetailsLoadingState
import com.shurdev.my_plants.screens.details.viewModel.MyPlantDetailsUiState
import com.shurdev.my_plants.screens.details.viewModel.MyPlantDetailsViewModel
import com.shurdev.my_plants.screens.details.composables.DeletePlantIconButton
import com.shurdev.ui_kit.errors.ErrorView
import com.shurdev.ui_kit.layouts.DefaultScreenLayout
import com.shurdev.ui_kit.loaders.Loader

@Composable
internal fun MyPlantDetailsRoute(
    plantId: Int,
    onBackInvoked: () -> Unit,
) {
    val viewModel =
        hiltViewModel<MyPlantDetailsViewModel, MyPlantDetailsViewModel.ViewModelFactory> { factory ->
            factory.create(plantId)
        }

    val uiState by viewModel.uiState.collectAsState()

    MyPlantDetailsScreen(
        uiState = uiState,
        onBackInvoked = onBackInvoked,
        onDeleteClick = viewModel::deletePlant,
    )
}

@Composable
internal fun MyPlantDetailsScreen(
    uiState: MyPlantDetailsUiState,
    onBackInvoked: () -> Unit,
    onDeleteClick: () -> Unit,
) {
    LaunchedEffect(uiState) {
        if (uiState is MyPlantDeletedState) {
            onBackInvoked()
        }
    }

    DefaultScreenLayout(
        modifier = Modifier.padding(horizontal = 16.dp),
        onBackInvoked = onBackInvoked,
        actions = {
            DeletePlantIconButton(
                onConfirm = onDeleteClick,
                icon = Icons.Default.Delete,
                contentDescription = null,
            )
        }
    ) {
        when (uiState) {
            MyPlantDeletedState -> {}
            is MyPlantDetailsLoadingState -> Loader()
            // TODO: Display error as toast
            is MyPlantDetailsErrorState -> ErrorView()
            is MyPlantDetailsLoadedState -> MyPlantDetailsContent(
                myPlant = uiState.plant,
            )
        }
    }
}

@Preview
@Composable
internal fun MyPlantDetailsScreenPreview() {
    MyPlantDetailsScreen(
        onBackInvoked = {},
        onDeleteClick = {},
        uiState = MyPlantDetailsLoadedState(
            plant = MyPlant(
                id = 1,
                name = "Тюльпан",
                imageData = null
            )
        )
    )
}
