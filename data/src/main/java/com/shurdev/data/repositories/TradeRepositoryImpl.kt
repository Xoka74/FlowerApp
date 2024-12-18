package com.shurdev.data.repositories

import com.shurdev.domain.models.Plant
import com.shurdev.domain.models.trade.Trade
import com.shurdev.domain.repositories.TradeRepository
import javax.inject.Inject

class TradeRepositoryImpl @Inject constructor() : TradeRepository {

    override suspend fun getTrades(): List<Trade> {
        return trades
    }

    override suspend fun getTradeById(id: Int): Trade? {
        return trades
            .firstOrNull { it.id == id }
    }

    override suspend fun createTrade(trade: Trade) {
        // TODO send created trade to server
        println("Create trade $trade")
    }


    private val trades: List<Trade> = (1..10)
        .map {
            Trade(
                plantToGet = Plant(
                    id = it,
                    name = "Get plant $it",
                    description = "Get description $it",
                    imageLink = "https://cdn.britannica.com/84/73184-050-05ED59CB/Sunflower-field-Fargo-North-Dakota.jpg",
                ),
                plantToGive = Plant(
                    id = it,
                    name = "Give plant $it",
                    description = "Give description $it",
                    imageLink = "https://cdn.britannica.com/84/73184-050-05ED59CB/Sunflower-field-Fargo-North-Dakota.jpg",
                ),
                authorName = "Author $it"
            )
        }
}