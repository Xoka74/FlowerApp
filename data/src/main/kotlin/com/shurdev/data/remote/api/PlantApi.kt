package com.shurdev.data.remote.api

import com.shurdev.data.models.ListPlantDto
import com.shurdev.data.models.PlantDto
import com.shurdev.domain.models.plant.PlantId
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlantApi {
    @GET("flowers/")
    suspend fun getPlants(
        @Query("searchString") query: String? = null,
        @Query("ToxicCategories") toxicCategories: String? = null,
        @Query("Illumination") illuminations: String? = null,
        @Query("WateringFrequency") frequencies: String? = null,
        @Query("SortField") sortField: String? = null,
        @Query("SortDirection") sortDirection: String? = null,
    ): ListPlantDto

    @GET("flowers/{id}")
    suspend fun getPlantById(@Path("id") id: PlantId): PlantDto
}