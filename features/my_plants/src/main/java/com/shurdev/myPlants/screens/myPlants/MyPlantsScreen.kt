package com.shurdev.myPlants.screens.myPlants

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
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
import com.shurdev.domain.models.Plant
import com.shurdev.myPlants.components.MyPlantItem
import com.shurdev.myPlants.screens.myPlants.viewModel.MyPlantsLoadedState
import com.shurdev.myPlants.screens.myPlants.viewModel.MyPlantsLoadingErrorState
import com.shurdev.myPlants.screens.myPlants.viewModel.MyPlantsLoadingState
import com.shurdev.myPlants.screens.myPlants.viewModel.MyPlantsUiState
import com.shurdev.myPlants.screens.myPlants.viewModel.MyPlantsViewModel
import com.shurdev.uiKit.buttons.PrimaryButton
import com.shurdev.uiKit.errors.ErrorView
import com.shurdev.uiKit.loaders.Loader
import com.shurdev.uiKit.theme.AppBackgroundColor
import com.shurdev.uiKit.theme.PlantCardContentColor
import com.shurdev.ui_kit.R

@Composable
internal fun MyPlantsRoute(
    onPlantClick: (Plant) -> Unit,
    viewModel: MyPlantsViewModel = hiltViewModel<MyPlantsViewModel>(),
) {
    val uiState by viewModel.uiState.collectAsState()

    MyPlantsScreen(
        uiState = uiState,
        onPlantClick = onPlantClick,
    )
}

// TODO: Refactor
@Composable
internal fun MyPlantsScreen(
    uiState: MyPlantsUiState,
    onPlantClick: (Plant) -> Unit,
) {
    when (uiState) {
        MyPlantsLoadingErrorState -> ErrorView()
        MyPlantsLoadingState -> Loader()
        is MyPlantsLoadedState -> {
            Scaffold(
                containerColor = AppBackgroundColor,
            ) { padding ->
                val myPlants = stringResource(R.string.my_plants)
                val addPlant = stringResource(R.string.add_plant)

                LazyColumn(
                    Modifier
                        .padding(padding)
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                ) {
                    item {
                        Text(
                            text = myPlants,
                            color = PlantCardContentColor,
                            fontSize = 24.sp,
                            style = TextStyle(fontWeight = FontWeight.Bold),
                        )

                        Spacer(Modifier.height(12.dp))

                        PrimaryButton(
                            text = addPlant,
                            onClick = {
                                // TODO: Navigate to add plant screen
                            },
                        )

                        Spacer(Modifier.height(12.dp))
                    }

                    items(uiState.plants) { plant ->
                        MyPlantItem(
                            plant = plant,
                            onItemClick = onPlantClick,
                        )
                    }
                }
            }
        }
    }
}
