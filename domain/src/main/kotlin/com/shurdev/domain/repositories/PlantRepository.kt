package com.shurdev.domain.repositories

import com.shurdev.domain.models.plant.Plant
import com.shurdev.domain.models.plant.PlantId
import com.shurdev.domain.models.plant.SearchOptions

interface PlantRepository {
    suspend fun getPlants(options: SearchOptions): List<Plant>

    suspend fun getPlantById(id: PlantId): Plant?
}