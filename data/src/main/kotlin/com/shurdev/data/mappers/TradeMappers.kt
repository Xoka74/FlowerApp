package com.shurdev.data.mappers

import com.shurdev.data.remote.dtos.CreateTradeDto
import com.shurdev.data.remote.dtos.GetTradeByIdDto
import com.shurdev.domain.models.trade.CreateTradeModel
import com.shurdev.domain.models.trade.PlantTrade
import com.shurdev.domain.models.trade.Trade

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

fun GetTradeByIdDto.toTrade(): Trade {
    return Trade(
        id = id,
        city = location,
        plantToGet = PlantTrade(
            name = preferredTrade,
            imageData = null
        ),
        plantToGive = PlantTrade(
            name = flowerName,
            imageData = photoBase64.toByteArray()
        ),
        authorName = userId, // TODO
        contactData = userId, // TODO
    )
}