package com.shurdev.domain.repositories

import com.shurdev.domain.models.myPlant.PlantWatering
import com.shurdev.domain.models.plant.PlantId

interface WateringAlertsRepository {
    fun schedule(
        plantName: String,
        plantId: PlantId,
        watering: PlantWatering,
    )

    fun cancel(
        plantName: String,
        plantId: PlantId,
    )

    fun cancelAll()
}