package com.shurdev.data.mappers

import com.shurdev.data.entities.MyPlantEntity
import com.shurdev.domain.models.myPlant.MyPlant
import com.shurdev.domain.models.myPlant.MyPlantData
import com.shurdev.domain.models.myPlant.MyPlantId

fun MyPlantEntity.toDomainModel(): MyPlant {
    return MyPlant(
        id = id,
        name = name,
        imageData = imageData,
        plantWatering = plantWatering,
        otherInfo = otherInfo,
    )
}

fun MyPlantData.toEntity(id: MyPlantId): MyPlantEntity {
    return MyPlantEntity(
        id = id,
        name = name,
        imageData = imageData,
        plantWatering = plantWatering,
        otherInfo = otherInfo,
    )
}