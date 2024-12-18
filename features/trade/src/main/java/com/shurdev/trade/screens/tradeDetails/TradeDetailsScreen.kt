package com.shurdev.trade.screens.tradeDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.shurdev.domain.models.Plant
import com.shurdev.domain.models.trade.Trade
import com.shurdev.trade.R
import com.shurdev.trade.screens.tradeDetails.viewModel.TradeDetailsLoadedState
import com.shurdev.trade.screens.tradeDetails.viewModel.TradeDetailsLoadingErrorState
import com.shurdev.trade.screens.tradeDetails.viewModel.TradeDetailsLoadingState
import com.shurdev.trade.screens.tradeDetails.viewModel.TradeDetailsUiState
import com.shurdev.trade.screens.tradeDetails.viewModel.TradeDetailsViewModel

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
        uiState = uiState
    )
}

@Composable
fun TradeDetailsScreen(
    uiState: TradeDetailsUiState
) {

    when (uiState) {
        is TradeDetailsLoadingState -> {}
        is TradeDetailsLoadingErrorState -> {}
        is TradeDetailsLoadedState -> {

            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {

                Text(
                    text = stringResource(R.string.exchange),
                    fontSize = 24.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )

                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                ) {

                    val trade = uiState.trade
                    val plantToGet = trade.plantToGet
                    val plantToGive = trade.plantToGive

                    val tradeText =
                        "${stringResource(R.string.exchange_plants_with)} ${trade.authorName}!";

                    val imageWidthPercentage = 0.8f
                    val cornerRadius = 12.dp

                    Text(
                        text = tradeText
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    Text(
                        text = stringResource(R.string.your_plant)
                    )

                    Text(
                        text = plantToGive.name,
                        fontSize = 20.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Medium
                    )

                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth(imageWidthPercentage)
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(cornerRadius)),
                        model = plantToGive.imageLink,
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )

                    Text(
                        text = plantToGive.description
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    Text(
                        text = stringResource(R.string.in_exchange_for)
                    )

                    Text(
                        text = plantToGet.name,
                        fontSize = 20.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Medium
                    )

                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth(imageWidthPercentage)
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(cornerRadius)),
                        model = plantToGet.imageLink,
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )

                    Text(
                        text = plantToGet.description
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
                    imageLink = "https://cdn.britannica.com/84/73184-050-05ED59CB/Sunflower-field-Fargo-North-Dakota.jpg"
                ),
                plantToGive = Plant(
                    name = "Тюльпан",
                    description = "Великолепный",
                    imageLink = "https://cdn.britannica.com/84/73184-050-05ED59CB/Sunflower-field-Fargo-North-Dakota.jpg"
                ),
                authorName = "Юрий"
            )
        )
    )
}