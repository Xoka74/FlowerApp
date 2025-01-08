package com.shurdev.trade.screens.trade

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import com.shurdev.trade.composables.TradeItem
import com.shurdev.trade.screens.trade.viewModel.TradeLoadedState
import com.shurdev.trade.screens.trade.viewModel.TradeLoadingErrorState
import com.shurdev.trade.screens.trade.viewModel.TradeLoadingState
import com.shurdev.trade.screens.trade.viewModel.TradeUiState
import com.shurdev.trade.screens.trade.viewModel.TradeViewModel
import com.shurdev.ui_kit.errors.ErrorView
import com.shurdev.ui_kit.layouts.DefaultScreenLayout
import com.shurdev.ui_kit.loaders.Loader

@Composable
internal fun TradeRoute(
    onTradeItemClick: (Trade) -> Unit = {},
    onCreateTradeClick: () -> Unit = {},
    onBackInvoked: () -> Unit = {},
) {

    val viewModel = hiltViewModel<TradeViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    TradeScreen(
        uiState = uiState,
        onTradeItemClick = onTradeItemClick,
        onCreateTradeClick = onCreateTradeClick,
        onBackInvoked = onBackInvoked,
    )
}

@Composable
internal fun TradeScreen(
    uiState: TradeUiState,
    onTradeItemClick: (Trade) -> Unit = {},
    onCreateTradeClick: () -> Unit = {},
    onBackInvoked: () -> Unit = {},
) {
    DefaultScreenLayout(
        modifier = Modifier.padding(horizontal = 16.dp),
        title = stringResource(R.string.plants_trade),
        onBackInvoked = onBackInvoked,
        fab = {
            FloatingActionButton(
                modifier = Modifier.padding(10.dp),
                onClick = onCreateTradeClick,
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Создать"
                )
            }
        },
    ) {
        when (uiState) {
            is TradeLoadingState -> Loader()
            is TradeLoadingErrorState -> ErrorView()
            is TradeLoadedState -> TradeScreenContent(
                onTradeItemClick = onTradeItemClick,
                trades = uiState.trades,
            )
        }
    }
}

@Composable
fun TradeScreenContent(
    onTradeItemClick: (Trade) -> Unit = {},
    trades: List<Trade>,
) {
    Box(
        contentAlignment = Alignment.BottomEnd,
    ) {
        Column {
            LazyColumn(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                items(trades) { trade ->
                    TradeItem(
                        trade = trade,
                        onItemClick = onTradeItemClick
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun TradeScreenPreview() {
    TradeScreen(
        uiState = TradeLoadedState(
            trades = listOf(
                Trade(
                    plantToGet = Plant(
                        name = "Роза",
                        description = "Колючая",
                        imageLink = "",
                    ),
                    plantToGive = Plant(
                        name = "Тюльпан",
                        description = "Большой",
                        imageLink = ""
                    ),
                    authorName = "Юрий"
                )
            )
        )
    )
}