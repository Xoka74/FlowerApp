package com.shurdev.trade.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.shurdev.domain.models.trade.Trade
import com.shurdev.trade.screens.createTrade.CreateTradeRoute
import com.shurdev.trade.screens.trade.TradeRoute
import com.shurdev.trade.screens.tradeDetails.TradeDetailsRoute
import kotlinx.serialization.Serializable

@Serializable
object TradeNavGraph

fun NavController.navigateToTradeGraph() = navigate(TradeNavGraph)

fun NavGraphBuilder.tradeNavGraph(
    onTradeItemClick: (Trade) -> Unit = {},
    onBackInvoked: () -> Unit,
    onCreateTradeClick: () -> Unit
) {
    navigation<TradeNavGraph>(
        startDestination = TradeRoute,
    ) {
        tradeScreen(
            onTradeItemClick = onTradeItemClick,
            onCreateTradeClick = onCreateTradeClick
        )

        tradeDetailsScreen(
            onBackInvoked = onBackInvoked
        )

        createTradeScreen(
            onBackInvoked = onBackInvoked
        )
    }
}


@Serializable
object TradeRoute

fun NavGraphBuilder.tradeScreen(
    onTradeItemClick: (Trade) -> Unit = {},
    onCreateTradeClick: () -> Unit
) {
    composable<TradeRoute> {
        TradeRoute(
            onTradeItemClick = onTradeItemClick,
            onCreateTradeClick = onCreateTradeClick,
        )
    }
}

@Serializable
data class TradeDetails(val tradeId: Int)

fun NavGraphBuilder.tradeDetailsScreen(
    onBackInvoked: () -> Unit
) {
    composable<TradeDetails> { backStackEntry ->

        val tradeDetails: TradeDetails = backStackEntry.toRoute<TradeDetails>()

        TradeDetailsRoute(
            tradeId = tradeDetails.tradeId,
            onBackInvoked = onBackInvoked
        )
    }
}


fun NavController.navigateToTradeDetailsScreen(tradeId: Int) {
    navigate(TradeDetails(tradeId))
}


@Serializable
object CreateTrade

fun NavGraphBuilder.createTradeScreen(
    onBackInvoked: () -> Unit
) {
    composable<CreateTrade> {

        CreateTradeRoute(
            onBackInvoked = onBackInvoked
        )
    }
}

fun NavController.navigateToCreateTradeScreen() {
    navigate(CreateTrade)
}