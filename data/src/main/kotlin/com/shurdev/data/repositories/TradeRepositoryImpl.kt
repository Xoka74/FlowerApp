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

    private val trades: List<Trade> = listOf(
        Trade(
            id = 1,
            plantToGet = Plant(
                id = 1,
                name = "Роза",
                description = "Потрясающая роза, " +
                        "ежедневно радующая глаз " +
                        "своей красотой",
                imageLink = "https://postila.ru/data/35/c9/c4/31/35c9c431d95e258318a20252c603ddc5feb288d5f9b31ac3a2ea23e4a2744eb4.jpg"
            ),
            plantToGive = Plant(
                id = 2,
                name = "Тюльпан",
                description = "Крутой",
                imageLink = "https://img.goodfon.com/original/1400x1050/1/69/tyulpan-lepestki-listya-2902.jpg"
            ),
            authorName = "Юрий +79382828282",
        ),
        Trade(
            id = 2,
            plantToGet = Plant(
                id = 3,
                name = "Орхидея",
                description = "",
                imageLink = "https://i.pinimg.com/736x/fd/a2/a1/fda2a1e77e3ac360a9edef98a45ff934.jpg"
            ),
            plantToGive = Plant(
                id = 4,
                name = "Алоэ",
                description = "Лечит от всех болезней",
                imageLink = "https://avatars.mds.yandex.net/get-entity_search/1783226/959809464/S600xU_2x"
            ),
            authorName = "Вячеслав Бисеров"
        )
    )
}