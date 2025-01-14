package com.shurdev.domain.repositories

import com.shurdev.domain.models.myPlant.MyPlant
import com.shurdev.domain.models.myPlant.MyPlantData
import com.shurdev.domain.models.myPlant.MyPlantId
import kotlinx.coroutines.flow.Flow

interface MyPlantsRepository {
    fun getAll(): Flow<List<MyPlant>>

    suspend fun getById(id: MyPlantId): MyPlant

    suspend fun getFlowById(id: MyPlantId): Flow<MyPlant?>

    suspend fun create(data: MyPlantData): Long

    suspend fun update(id: MyPlantId, data: MyPlantData)

    suspend fun delete(
        plantId: MyPlantId,
        plantName: String,
    )
}