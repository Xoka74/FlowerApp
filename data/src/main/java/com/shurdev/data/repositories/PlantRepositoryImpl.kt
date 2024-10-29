package com.shurdev.data.repositories

import com.shurdev.domain.models.Plant
import com.shurdev.domain.models.PlantFilters
import com.shurdev.domain.repositories.PlantRepository
import javax.inject.Inject

class PlantRepositoryImpl @Inject constructor() : PlantRepository {

    override suspend fun getPlants(filters: PlantFilters?): List<Plant> {
        return plants.filter { item ->
            filters?.let {
                doesPlantMatchFilters(item, it)
            } ?: true
        }
    }

    override suspend fun getPlantById(id: Int): Plant? = plants.firstOrNull { it.id == id }

    private val plants: List<Plant> = (0..20).map {
        Plant(
            id = it,
            name = "Plant${it}",
            description = "Description${it}",
            imageLink = "https://cdn.britannica.com/84/73184-050-05ED59CB/Sunflower-field-Fargo-North-Dakota.jpg"
        )
    }

    private fun doesPlantMatchFilters(plant: Plant, filters: PlantFilters): Boolean {
        return plant.name.contains(filters.name ?: "", ignoreCase = true)
                || plant.description.contains(filters.description ?: "", ignoreCase = true)
    }
}