package com.shurdev.data.repositories

import com.shurdev.data.mappers.toDomainModel
import com.shurdev.data.mappers.toSortDirection
import com.shurdev.data.mappers.toSortField
import com.shurdev.data.remote.api.PlantApi
import com.shurdev.domain.models.plant.Plant
import com.shurdev.domain.models.plant.PlantId
import com.shurdev.domain.models.plant.SearchOptions
import com.shurdev.domain.repositories.PlantRepository
import javax.inject.Inject

class PlantRepositoryImpl @Inject constructor(
    private val plantApi: PlantApi,
) : PlantRepository {

    override suspend fun getPlants(options: SearchOptions): List<Plant> {
        val result = plantApi.getPlants(
            query = options.search,
            toxicCategories = if (options.filters.toxicCategories.isEmpty()) null else options.filters.toxicCategories.joinToString(
                ","
            ),
            illuminations = if (options.filters.illuminations.isEmpty()) null else options.filters.illuminations.joinToString(
                ","
            ),
            frequencies = if (options.filters.wateringFrequencies.isEmpty()) null else options.filters.wateringFrequencies.joinToString(
                ","
            ),
            sortField = options.sorting.toSortField().toString(),
            sortDirection = options.sorting.toSortDirection().toString(),
        )

        return result.flowers.map { it.toDomainModel() }
    }

    override suspend fun getPlantById(id: PlantId): Plant {
        return plantApi.getPlantById(id).toDomainModel()
    }
}