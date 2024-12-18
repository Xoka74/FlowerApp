package com.shurdev.domain.usecases

import com.shurdev.domain.models.myPlant.MyPlantId
import com.shurdev.domain.repositories.MyPlantsRepository
import com.shurdev.domain.repositories.WateringAlertsRepository

class DeleteMyPlantUseCase(
    private val myPlantsRepository: MyPlantsRepository,
    private val wateringAlertsRepository: WateringAlertsRepository,
) {
    suspend operator fun invoke(
        id: MyPlantId,
        name: String,
    ) {
        // TODO: Add image deleting
        myPlantsRepository.delete(id, name)

        wateringAlertsRepository.cancel(
            plantId = id,
            plantName = name,
        )
    }
}