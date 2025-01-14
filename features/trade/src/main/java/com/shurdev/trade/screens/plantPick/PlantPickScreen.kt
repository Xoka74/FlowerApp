package com.shurdev.trade.screens.plantPick

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shurdev.domain.models.trade.PlantTrade
import com.shurdev.trade.R
import com.shurdev.trade.composables.plantPick.PlantsList
import com.shurdev.trade.models.MyPlantPresentation
import com.shurdev.trade.screens.plantPick.viewModel.PlantPickLoadedState
import com.shurdev.trade.screens.plantPick.viewModel.PlantPickLoadingErrorState
import com.shurdev.trade.screens.plantPick.viewModel.PlantPickLoadingState
import com.shurdev.trade.screens.plantPick.viewModel.PlantPickUiState
import com.shurdev.trade.screens.plantPick.viewModel.PlantPickViewModel
import com.shurdev.ui_kit.errors.ErrorView
import com.shurdev.ui_kit.fields.SearchField
import com.shurdev.ui_kit.loaders.Loader

enum class PlantPickType(val type: String) {
    PlantToGive("PLANT_TO_GIVE"),
    PlantToGet("PLANT_TO_GET"),
}

@Composable
fun PlantPickRoute(
    plantPickType: PlantPickType,
    onBackInvoked: () -> Unit,
    onPlantClicked: (PlantTrade) -> Unit,
) {

    val viewModel =
        hiltViewModel<PlantPickViewModel, PlantPickViewModel.ViewModelFactory> { factory ->
            factory.create(plantPickType = plantPickType)
        }
    val uiState by viewModel.uiState.collectAsState()

    PlantPickScreen(
        uiState = uiState,
        plantPickType = plantPickType,
        onSearchTextChange = viewModel::onSearchTextText,
        onPlantClick = {
            println("Click plant $it")
            val plantTrade = PlantTrade(name = it.name, imageData = it.imageData)
            onPlantClicked(plantTrade)
        }
    )
}

@Composable
fun PlantPickScreen(
    uiState: PlantPickUiState,
    plantPickType: PlantPickType,
    onSearchTextChange: (String) -> Unit,
    onPlantClick: (MyPlantPresentation) -> Unit,
) {

    val pickPlantTitle = if (plantPickType == PlantPickType.PlantToGet)
        stringResource(R.string.desired_plant)
    else
        stringResource(R.string.give_plant)

    Scaffold { padding ->
        when (uiState) {
            is PlantPickLoadedState -> {
                Column(
                    modifier = Modifier
                ) {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(top = 20.dp)
                            .fillMaxWidth(),
                        text = pickPlantTitle,
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

                    PlantsList(
                        plants = uiState.plants,
                        onPlantClick = onPlantClick
                    )
                }
            }

            PlantPickLoadingErrorState -> ErrorView()
            PlantPickLoadingState -> Loader()
        }
    }
}