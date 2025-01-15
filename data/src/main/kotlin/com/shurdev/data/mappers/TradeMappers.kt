package com.shurdev.data.mappers

import com.shurdev.data.remote.dtos.TradeDto
import com.shurdev.domain.models.trade.CreateTradeModel
import com.shurdev.domain.models.trade.PlantTrade
import com.shurdev.domain.models.trade.Trade
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

fun CreateTradeModel.toCreateTradeDto(): TradeDto {
    return TradeDto(
        id = id,
        description = description,
        location = city,
        preferredTrade = plantToGetName,
        flowerName = plantToGiveName,
        expiresAt = "2026-01-14T19:56:44.529Z",
        photoBase64 = plantToGiveImage,
        isActive = true,
        authorName = authorName,
        contactData = contactData,
    )
}

@OptIn(ExperimentalEncodingApi::class)
fun TradeDto.toTrade(): Trade {
    return Trade(
        id = id,
        city = location,
        plantToGet = PlantTrade(
            name = preferredTrade,
            imageData = null
        ),
        plantToGive = PlantTrade(
            name = flowerName,
            imageData =  Base64.decode(photoBase64)
        ),
        authorName = authorName,
        contactData = contactData,
    )
}