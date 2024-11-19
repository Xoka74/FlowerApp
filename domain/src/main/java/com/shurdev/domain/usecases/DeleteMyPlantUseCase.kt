package com.shurdev.domain.usecases

import com.shurdev.domain.models.MyPlantId
import com.shurdev.domain.repositories.MyPlantsRepository

class DeleteMyPlantUseCase(
    private val myPlantsRepository: MyPlantsRepository,
) {
    suspend operator fun invoke(id: MyPlantId) {
        // TODO: Add image deleting
        myPlantsRepository.delete(id)
    }
}