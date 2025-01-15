package com.shurdev.data.remote.api

import com.shurdev.data.remote.dtos.TradeDto
import com.shurdev.data.remote.dtos.getTradeById.GetTradeByIdDto
import com.shurdev.data.remote.dtos.getTrades.GetTradesDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface TradeApi {

    @GET("/api/trades")
    suspend fun getTrades(): GetTradesDto?

    @GET("/api/trades/{id}")
    suspend fun getTradeById(
        @Path("id") int: Int,
    ): GetTradeByIdDto?

    @POST("/api/trades")
    suspend fun createTrade(
        @Body trade: TradeDto
    )

    @PATCH("/api/trades/{id}/deactivate")
    suspend fun confirmTrade(
        @Path("id") id: Int
    )
}