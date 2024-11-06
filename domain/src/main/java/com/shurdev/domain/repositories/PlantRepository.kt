package com.shurdev.domain.repositories

import com.shurdev.domain.models.Plant
import com.shurdev.domain.models.PlantFilters

interface PlantRepository {
    suspend fun getPlants(filters: PlantFilters? = null): List<Plant>

    suspend fun getPlantById(id: Int): Plant?
}
