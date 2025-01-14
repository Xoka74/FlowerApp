package com.shurdev.trade.screens.tradeDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shurdev.domain.models.plant.Plant
import com.shurdev.domain.models.trade.Trade
import com.shurdev.trade.R
import com.shurdev.trade.composables.PlantCard
import com.shurdev.trade.screens.tradeDetails.viewModel.TradeDetailsLoadedState
import com.shurdev.trade.screens.tradeDetails.viewModel.TradeDetailsLoadingErrorState
import com.shurdev.trade.screens.tradeDetails.viewModel.TradeDetailsLoadingState
import com.shurdev.trade.screens.tradeDetails.viewModel.TradeDetailsUiState
import com.shurdev.trade.screens.tradeDetails.viewModel.TradeDetailsViewModel
import com.shurdev.ui_kit.layouts.DefaultScreenLayout

@Composable
fun TradeDetailsRoute(
    tradeId: Int,
    onBackInvoked: () -> Unit,
) {

    val viewModel =
        hiltViewModel<TradeDetailsViewModel, TradeDetailsViewModel.ViewModelFactory> { factory ->
            factory.create(tradeId = tradeId)
        }
    val uiState by viewModel.uiState.collectAsState()

    TradeDetailsScreen(
        uiState = uiState,
        onBackInvoked = onBackInvoked
    )
}

@Composable
fun TradeDetailsScreen(
    uiState: TradeDetailsUiState,
    onBackInvoked: () -> Unit,
) {

    when (uiState) {
        is TradeDetailsLoadingState -> {}
        is TradeDetailsLoadingErrorState -> {}
        is TradeDetailsLoadedState -> {

            val trade = uiState.trade

            val plantToGet = trade.plantToGet
            val plantToGive = trade.plantToGive

            val titleText = stringResource(R.string.exchange) + " с ${trade.authorName}"

            DefaultScreenLayout(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState()),
                onBackInvoked = onBackInvoked,
                title = titleText,
            ) {

                Column {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.LocationOn,
                            contentDescription = ""
                        )

                        Text(
                            text = "Москва"
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    Text(
                        text = stringResource(R.string.your_plant)
                    )

                    PlantCard(
                        plant = plantToGive
                    )


                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    Text(
                        text = stringResource(R.string.in_exchange_for)
                    )

                    PlantCard(
                        plant = plantToGet
                    )

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TradeDetailsScreenPreview() {
    TradeDetailsScreen(
        uiState = TradeDetailsLoadedState(
            trade = Trade(
                id = 1,
                plantToGet = Plant(
                    name = "Роза",
                    description = "Колючая",
                ),
                plantToGive = Plant(
                    name = "Тюльпан",
                    description = "Великолепный",
                ),
                authorName = "Юрий"
            )
        ),
        onBackInvoked = {}
    )
}