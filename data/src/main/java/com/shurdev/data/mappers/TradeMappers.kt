package com.shurdev.data.mappers

import com.shurdev.data.remote.dtos.CreateTradeDto
import com.shurdev.domain.models.trade.CreateTradeModel

fun CreateTradeModel.toCreateTradeDto(): CreateTradeDto{
    return CreateTradeDto(
        id = id,
        description = description,
        location = city,
        preferredTrade = plantToGetName,
        flowerName = plantToGiveName,
        expiresAt = "2025-01-14T19:56:44.529Z", // TODO
        photoBase64 = plantToGiveImage,
        userId = "123", // TODO
        isActive = true
    )
}