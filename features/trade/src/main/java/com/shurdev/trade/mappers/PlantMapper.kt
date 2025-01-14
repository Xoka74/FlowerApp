package com.shurdev.trade.mappers

import com.shurdev.domain.models.plant.Plant
import com.shurdev.trade.models.PlantPresentation

fun Plant?.toPresentationModel(): PlantPresentation? {
    if (this == null) {
        return null;
    }

    return PlantPresentation(
        id = id,
        name = name,
        description = description,
        imageLink = imageLink,
    )
}

fun PlantPresentation?.toDomainModel(): Plant? {
    if (this == null) {
        return null;
    }

    return Plant(
        id = id,
        name = name,
        description = description,
        imageLink = imageLink,
    )
}