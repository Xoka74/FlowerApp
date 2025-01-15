package com.shurdev.data.remote.dtos.getTradeById

import com.shurdev.data.remote.dtos.TradeDto

data class GetTradeByIdDto(
    val code: String,
    val data: TradeDto?,
    val message: String
)