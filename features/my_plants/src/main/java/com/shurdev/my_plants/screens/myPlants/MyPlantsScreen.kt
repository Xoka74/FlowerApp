package com.shurdev.my_plants.screens.myPlants

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shurdev.domain.models.MyPlant
import com.shurdev.my_plants.components.MyPlantItem
import com.shurdev.my_plants.screens.myPlants.viewModel.MyPlantsLoadedState
import com.shurdev.my_plants.screens.myPlants.viewModel.MyPlantsLoadingErrorState
import com.shurdev.my_plants.screens.myPlants.viewModel.MyPlantsLoadingState
import com.shurdev.my_plants.screens.myPlants.viewModel.MyPlantsUiState
import com.shurdev.my_plants.screens.myPlants.viewModel.MyPlantsViewModel
import com.shurdev.ui_kit.R
import com.shurdev.ui_kit.buttons.PrimaryButton
import com.shurdev.ui_kit.errors.ErrorView
import com.shurdev.ui_kit.loaders.Loader
import com.shurdev.ui_kit.theme.PlantCardContentColor

@Composable
internal fun MyPlantsRoute(
    onPlantClick: (MyPlant) -> Unit,
    onAddPlantClick: () -> Unit,
) {
    val myPlantsViewModel = hiltViewModel<MyPlantsViewModel>()

    val uiState by myPlantsViewModel.uiState.collectAsState()

    MyPlantsScreen(
        uiState = uiState,
        onPlantClick = onPlantClick,
        onAddPlantClick = onAddPlantClick,
    )
}

@Composable
internal fun MyPlantsScreen(
    uiState: MyPlantsUiState,
    onPlantClick: (MyPlant) -> Unit,
    onAddPlantClick: () -> Unit,
) {
    when (uiState) {
        MyPlantsLoadingErrorState -> ErrorView()
        MyPlantsLoadingState -> Loader()
        is MyPlantsLoadedState -> MyPlantsScreenContent(
            plants = uiState.plants,
            onPlantClick = onPlantClick,
            onAddPlantClick = onAddPlantClick,
        )
    }
}

@Composable
internal fun MyPlantsScreenContent(
    plants: List<MyPlant>,
    onPlantClick: (MyPlant) -> Unit,
    onAddPlantClick: () -> Unit,
) {
    val myPlants = stringResource(R.string.my_plants)
    val addPlant = stringResource(R.string.add_plant)

    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            text = myPlants,
            color = PlantCardContentColor,
            fontSize = 24.sp,
            style = TextStyle(fontWeight = FontWeight.Bold)
        )

        Spacer(Modifier.height(12.dp))

        LazyColumn(
            Modifier
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            items(plants) { plant ->
                MyPlantItem(
                    plant = plant,
                    onItemClick = onPlantClick
                )
            }
        }

        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = addPlant,
            onClick = onAddPlantClick,
        )
    }
}