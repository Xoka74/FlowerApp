package com.shurdev.domain.usecases

import com.shurdev.domain.models.CreateMyPlantIntent
import com.shurdev.domain.repositories.MyPlantsRepository

class CreateMyPlantUseCase(
    private val myPlantsRepository: MyPlantsRepository,
) {
    suspend operator fun invoke(intent: CreateMyPlantIntent) {
        // TODO: Add image creating
        myPlantsRepository.create(intent)
    }
}