package com.shurdev.domain.usecases

import com.shurdev.domain.models.myPlant.CreateMyPlantIntent
import com.shurdev.domain.repositories.MyPlantsRepository
import com.shurdev.domain.repositories.WateringAlertsRepository

class CreateMyPlantUseCase(
    private val myPlantsRepository: MyPlantsRepository,
    private val wateringAlertsRepository: WateringAlertsRepository,
) {
    suspend operator fun invoke(intent: CreateMyPlantIntent) {
        // TODO: Add image creating
        val id = myPlantsRepository.create(intent)

        val watering = intent.plantWatering

        if (watering != null) {
            wateringAlertsRepository.schedule(
                plantId = id.toInt(),
                plantName = intent.name,
                watering = watering,
            )
        }
    }
}