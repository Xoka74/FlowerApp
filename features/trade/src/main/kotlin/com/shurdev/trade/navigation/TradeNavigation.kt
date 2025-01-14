package com.shurdev.trade.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.shurdev.domain.models.trade.PlantTrade
import com.shurdev.domain.models.trade.Trade
import com.shurdev.trade.screens.createTrade.CreateTradeRoute
import com.shurdev.trade.screens.plantPick.PlantPickRoute
import com.shurdev.trade.screens.plantPick.PlantPickType
import com.shurdev.trade.screens.trade.TradeRoute
import com.shurdev.trade.screens.tradeDetails.TradeDetailsRoute
import kotlinx.serialization.Serializable

@Serializable
object TradeNavGraph

fun NavController.navigateToTradeGraph() = navigate(TradeNavGraph)

fun NavGraphBuilder.tradeNavGraph(
    onTradeItemClick: (Trade) -> Unit = {},
    onBackInvoked: () -> Unit,
    onCreateTradeClick: () -> Unit,
    onPlantToGetClicked: () -> Unit,
//    onPlantToGetPicked: (Plant) -> Unit,
    onPlantPicked: (PlantTrade, PlantPickType) -> Unit,
    onPlantToGiveClicked: () -> Unit,
//    onPlantToGivePicked: (Plant) -> Unit,
    getPlantToGive: () -> PlantTrade?,
    getPlantToGet: () -> PlantTrade? = { null },
) {
    navigation<TradeNavGraph>(
        startDestination = TradeRoute,
    ) {
        tradeScreen(
            onTradeItemClick = onTradeItemClick,
            onCreateTradeClick = onCreateTradeClick,
            onBackInvoked = onBackInvoked,
        )

        tradeDetailsScreen(
            onBackInvoked = onBackInvoked
        )

        createTradeScreen(
            onBackInvoked = onBackInvoked,
            onPlantToGetClicked = onPlantToGetClicked,
            onPlantToGiveClicked = onPlantToGiveClicked,
            getPlantToGet = getPlantToGet,
            getPlantToGive = getPlantToGive,
        )

        plantPickScreen(
            onBackInvoked = onBackInvoked,
            onPlantPicked = onPlantPicked
        )
    }
}


@Serializable
object TradeRoute

fun NavGraphBuilder.tradeScreen(
    onTradeItemClick: (Trade) -> Unit = {},
    onCreateTradeClick: () -> Unit = {},
    onBackInvoked: () -> Unit = {},
) {
    composable<TradeRoute> {
        TradeRoute(
            onTradeItemClick = onTradeItemClick,
            onCreateTradeClick = onCreateTradeClick,
            onBackInvoked = onBackInvoked,
        )
    }
}

@Serializable
data class TradeDetails(val tradeId: Int)

fun NavGraphBuilder.tradeDetailsScreen(
    onBackInvoked: () -> Unit,
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
data object CreateTrade

fun NavGraphBuilder.createTradeScreen(
    onBackInvoked: () -> Unit,
    onPlantToGetClicked: () -> Unit,
    onPlantToGiveClicked: () -> Unit,
    getPlantToGet: () -> PlantTrade?,
    getPlantToGive: () -> PlantTrade?,
) {
    composable<CreateTrade> {

        CreateTradeRoute(
            onBackInvoked = onBackInvoked,
            onPlantToGetClicked = onPlantToGetClicked,
            onPlantToGiveClicked = onPlantToGiveClicked,
            getPlantToGet = getPlantToGet,
            getPlantToGive = getPlantToGive
        )
    }
}

fun NavController.navigateToCreateTradeScreen() {
    navigate(
        CreateTrade
    )
}

@Serializable
data class PlantPick(
    val plantPickType: PlantPickType,
)

fun NavGraphBuilder.plantPickScreen(
    onBackInvoked: () -> Unit,
    onPlantPicked: (PlantTrade, PlantPickType) -> Unit,
) {
    composable<PlantPick> { backStackEntry ->

        val plantPickType = backStackEntry.toRoute<PlantPick>().plantPickType

        PlantPickRoute(
            plantPickType = plantPickType,
            onBackInvoked = onBackInvoked,
            onPlantClicked = { onPlantPicked(it, plantPickType) }
        )
    }
}

fun NavController.navigateToPlantPickScreen(
    plantPickType: PlantPickType
) {
    navigate(PlantPick(plantPickType = plantPickType))
}