package com.shurdev.data.models

import com.shurdev.domain.models.plant.PlantId

data class PlantDto(
    val id: PlantId,
    val name: String,
    val scientificName: String,
    val appearanceDescription: String,
    val size: Int,
    val photoBase64: String,
)