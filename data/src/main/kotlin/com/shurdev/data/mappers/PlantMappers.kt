package com.shurdev.data.mappers

import com.shurdev.data.models.PlantDto
import com.shurdev.domain.models.plant.Plant
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi


@OptIn(ExperimentalEncodingApi::class)
fun PlantDto.toDomainModel(): Plant {
    return Plant(
        id = id,
        name = name,
        description = appearanceDescription,
        imageLink = Base64.decode(photoBase64),
    )
}