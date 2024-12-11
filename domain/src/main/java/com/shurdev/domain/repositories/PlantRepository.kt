package com.shurdev.domain.repositories

import com.shurdev.domain.models.plant.Plant
import com.shurdev.domain.models.plant.PlantFilters


interface PlantRepository {
    suspend fun getPlants(filters: PlantFilters? = null): List<Plant>

    suspend fun getRecommendedPlants(): List<Plant>

    suspend fun getPlantById(id: Int): Plant?
}