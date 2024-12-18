package com.shurdev.domain.repositories

import com.shurdev.domain.models.trade.Trade

interface TradeRepository {

    suspend fun getTrades(): List<Trade>

    suspend fun getTradeById(id: Int): Trade?

    suspend fun createTrade(trade: Trade)
}