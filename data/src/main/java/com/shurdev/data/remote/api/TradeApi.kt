package com.shurdev.data.remote.api

import com.shurdev.domain.models.trade.Trade
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TradeApi {

    @GET("/api/trades")
    suspend fun getTrades(): List<Trade>

    @GET("/api/trades/{id}")
    suspend fun getTradeById(
        @Path("id") int: Int,
    ): Trade?

    @POST("/api/trades")
    suspend fun createTrade(
        @Body trade: Trade
    )

    @POST("/api/trades/{id}/confirm")
    suspend fun confirmTrade(
        @Path("id") id: Int
    )
}