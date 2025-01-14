package com.shurdev.trade.mappers

import com.shurdev.domain.models.myPlant.MyPlant
import com.shurdev.domain.models.trade.PlantTrade
import com.shurdev.trade.models.MyPlantPresentation

fun MyPlant?.toPresentationModel(): MyPlantPresentation? {
    if (this == null) {
        return null
    }

    return MyPlantPresentation(
        name = name,
        imageData = imageData
    )
}

fun MyPlantPresentation.toDomainModel(): MyPlant {
    return MyPlant(
        id = 0,
        name = name,
        imageData = null,
        otherInfo = null,
        plantWatering = null,
    )
}

fun MyPlantPresentation.toPlantTrade(): PlantTrade {
    return PlantTrade(
        name = name,
        imageData = imageData
    )
}