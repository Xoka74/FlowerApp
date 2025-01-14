package com.shurdev.data.repositories

import com.shurdev.data.mappers.toCreateTradeDto
import com.shurdev.data.mappers.toTrade
import com.shurdev.data.remote.api.TradeApi
import com.shurdev.domain.models.trade.CreateTradeModel
import com.shurdev.domain.models.trade.PlantTrade
import com.shurdev.domain.models.trade.Trade
import com.shurdev.domain.repositories.TradeRepository
import javax.inject.Inject

class TradeRepositoryImpl @Inject constructor(
    private val tradeApi: TradeApi
) : TradeRepository {

    override suspend fun getTrades(): List<Trade> {
//        val tradesDtos = tradeApi.getTrades()
        return trades
    }

    override suspend fun getTradeById(id: Int): Trade? {
        val tradeFromApi = tradeApi.getTradeById(id)
        return tradeFromApi?.toTrade()
//        return trades
//            .firstOrNull { it.id == id }
    }

    override suspend fun createTrade(trade: CreateTradeModel) {
        tradeApi.createTrade(trade.toCreateTradeDto())
    }

    override suspend fun confirmTrade(tradeId: Int) {
        tradeApi.confirmTrade(tradeId)
    }

    private val trades: List<Trade> = (1..10)
        .map {
            Trade(
                id = it,
                plantToGet = PlantTrade(
//                    id = it,
                    name = "Get plant $it",
                    imageData = null,
//                    description = "Get description $it",
//                    imageLink = "https://cdn.britannica.com/84/73184-050-05ED59CB/Sunflower-field-Fargo-North-Dakota.jpg",
                ),
                plantToGive = PlantTrade(
//                    id = it,
                    name = "Give plant $it",
                    imageData = null,
//                    description = "Give description $it",
//                    imageLink = "https://cdn.britannica.com/84/73184-050-05ED59CB/Sunflower-field-Fargo-North-Dakota.jpg",
                ),
                authorName = "Author $it",
                city = "City $it",
                contactData = "Contact $it"
            )
        }
}