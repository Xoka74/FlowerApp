package com.shurdev.data.remote.dtos.getTrades

import com.shurdev.data.remote.dtos.TradeDto

data class Data(
    val count: Int?,
    val trades: List<TradeDto>?
)