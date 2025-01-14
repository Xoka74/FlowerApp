package com.shurdev.data.repositories

import com.shurdev.domain.models.plant.Plant
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
    }


    private val trades: List<Trade> = (1..10)
        .map {
            Trade(
                id = it,
                plantToGet = Plant(
                    id = it,
                    name = "Get plant $it",
                    description = "Get description $it",
                ),
                plantToGive = Plant(
                    id = it,
                    name = "Give plant $it",
                    description = "Give description $it",
                ),
                authorName = "Author $it"
            )
        }
}