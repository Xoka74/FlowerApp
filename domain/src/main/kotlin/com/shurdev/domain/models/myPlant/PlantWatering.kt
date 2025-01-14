package com.shurdev.domain.models.myPlant

import com.shurdev.domain.models.plant.WateringFrequency
import java.time.LocalDateTime

data class PlantWatering(
    val lastWateringTime: LocalDateTime,
    val frequency: WateringFrequency = WateringFrequency.OnceAWeek,
)