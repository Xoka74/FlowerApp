package com.shurdev.trade.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.shurdev.domain.models.trade.Trade
import com.shurdev.trade.TradeRoute
import kotlinx.serialization.Serializable

@Serializable
object TradeNavGraph

fun NavController.navigateToTradeGraph() = navigate(TradeNavGraph)

fun NavGraphBuilder.tradeNavGraph(
    onTradeItemClick: (Trade) -> Unit = {}
) {
    navigation<TradeNavGraph>(
        startDestination = TradeRoute,
    ) {
        tradeScreen(
            onTradeItemClick = onTradeItemClick
        )
    }
}


@Serializable
object TradeRoute

fun NavGraphBuilder.tradeScreen(
    onTradeItemClick: (Trade) -> Unit = {}
) {
    composable<TradeRoute> {
        TradeRoute(
            onTradeItemClick = onTradeItemClick
        )
    }
}