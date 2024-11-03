package com.shurdev.domain.repositories

import com.shurdev.domain.models.flower.Plant
import com.shurdev.domain.models.flower.PlantFilters


interface PlantRepository {
    suspend fun getPlants(filters: PlantFilters? = null): List<Plant>

    suspend fun getPlantById(id: Int): Plant?
}