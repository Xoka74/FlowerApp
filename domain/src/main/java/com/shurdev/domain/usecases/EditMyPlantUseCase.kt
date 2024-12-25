package com.shurdev.domain.usecases

import com.shurdev.domain.models.myPlant.MyPlantData
import com.shurdev.domain.models.myPlant.MyPlantId
import com.shurdev.domain.repositories.MyPlantsRepository
import com.shurdev.domain.repositories.WateringAlertsRepository

class EditMyPlantUseCase(
    private val myPlantsRepository: MyPlantsRepository,
    private val wateringAlertsRepository: WateringAlertsRepository,
) {
    // TODO: Refactor
    suspend operator fun invoke(
        id: MyPlantId,
        data: MyPlantData,
    ) {
        val oldEntity = myPlantsRepository.getById(id)

        myPlantsRepository.update(id, data)

        val newWatering = data.plantWatering

        wateringAlertsRepository.cancel(oldEntity.name, id)

        if (newWatering != null) {
            wateringAlertsRepository.schedule(
                plantId = id,
                plantName = data.name,
                watering = newWatering,
            )
        }
    }
}