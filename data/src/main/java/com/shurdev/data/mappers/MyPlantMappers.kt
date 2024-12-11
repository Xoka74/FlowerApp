package com.shurdev.data.mappers

import com.shurdev.data.entities.MyPlantEntity
import com.shurdev.domain.models.myPlant.MyPlant

fun MyPlantEntity.toDomainModel(): MyPlant {
    return MyPlant(
        id = id,
        name = name,
        plantWatering = plantWatering,
        otherInfo = otherInfo,
    )
}