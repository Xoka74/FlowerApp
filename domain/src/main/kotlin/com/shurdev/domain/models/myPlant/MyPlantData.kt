package com.shurdev.domain.models.myPlant

data class MyPlantData(
    val name: String,
    val plantWatering: PlantWatering? = null,
    val otherInfo: PlantOtherInfo? = null,
    val imageData: ByteArray?,
)