package com.shurdev.my_plants

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
import com.shurdev.domain.models.Flower
import com.shurdev.my_plants.components.MyPlantItem
import com.shurdev.my_plants.view_model.MyPlantsLoadedState
import com.shurdev.my_plants.view_model.MyPlantsLoadingErrorState
import com.shurdev.my_plants.view_model.MyPlantsLoadingState
import com.shurdev.my_plants.view_model.MyPlantsUiState
import com.shurdev.my_plants.view_model.MyPlantsViewModel
import com.shurdev.ui_kit.components.buttons.PrimaryButton
import com.shurdev.ui_kit.errors.ErrorView
import com.shurdev.ui_kit.loaders.Loader
import com.shurdev.ui_kit.theme.AppBackgroundColor
import com.shurdev.ui_kit.theme.FlowerCardContentColor

@Composable
internal fun MyPlantsRoute(
    onFlowerClick: (Flower) -> Unit,
) {
    val myPlantsViewModel = hiltViewModel<MyPlantsViewModel>()

    val uiState by myPlantsViewModel.uiState.collectAsState()

    MyPlantsScreen(
        uiState = uiState,
        onFlowerClick = onFlowerClick,
    )
}

@Composable
internal fun MyPlantsScreen(
    uiState: MyPlantsUiState,
    onFlowerClick: (Flower) -> Unit,
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
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    item {
                        Text(
                            text = myPlants,
                            color = FlowerCardContentColor,
                            fontSize = 24.sp,
                            style = TextStyle(fontWeight = FontWeight.Bold)
                        )

                        Spacer(Modifier.height(12.dp))

                        PrimaryButton(
                            text = addPlant,
                            onClick = {
                                // Go to add plant screen
                            },
                        )

                        Spacer(Modifier.height(12.dp))
                    }

                    items(uiState.flowers) { flower ->
                        MyPlantItem(
                            flower = flower,
                            onItemClick = onFlowerClick
                        )
                    }
                }
            }
        }
    }
}