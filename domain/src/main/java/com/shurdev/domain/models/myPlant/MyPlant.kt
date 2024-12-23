package com.shurdev.domain.models.myPlant

typealias MyPlantId = Int

data class MyPlant(
    val id: MyPlantId,
    val name: String,
    val plantWatering: PlantWatering? = null,
    val otherInfo: PlantOtherInfo? = null,
    val imageData: ByteArray?,
)