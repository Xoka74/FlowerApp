package com.shurdev.trade

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shurdev.domain.models.Plant
import com.shurdev.domain.models.trade.Trade
import com.shurdev.trade.composables.TradeItem
import com.shurdev.trade.viewModel.TradeLoadedState
import com.shurdev.trade.viewModel.TradeLoadingErrorState
import com.shurdev.trade.viewModel.TradeLoadingState
import com.shurdev.trade.viewModel.TradeUiState
import com.shurdev.trade.viewModel.TradeViewModel
import com.shurdev.ui_kit.buttons.PrimaryButton
import com.shurdev.ui_kit.theme.PlantCardContentColor

@Composable
internal fun TradeRoute(
    onTradeItemClick: (Trade) -> Unit = {}
) {

    val viewModel = hiltViewModel<TradeViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    TradeScreen(
        uiState = uiState,
        onTradeItemClick = onTradeItemClick
    )
}

@Composable
internal fun TradeScreen(
    uiState: TradeUiState,
    onTradeItemClick: (Trade) -> Unit = {},
    onCreateTradeClick: () -> Unit = {}
) {

    when (uiState) {
        is TradeLoadingState -> {}
        is TradeLoadingErrorState -> {}
        is TradeLoadedState -> {

            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {

                val trades = uiState.trades

                Text(
                    text = stringResource(R.string.plants_trade),
                    color = PlantCardContentColor,
                    fontSize = 24.sp,
                    style = TextStyle(fontWeight = FontWeight.Bold)
                )

                Spacer(Modifier.height(12.dp))

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

                PrimaryButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.create_trade),
                    onClick = onCreateTradeClick,
                )
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