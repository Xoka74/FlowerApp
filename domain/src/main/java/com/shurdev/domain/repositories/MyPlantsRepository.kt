package com.shurdev.domain.repositories

import com.shurdev.domain.models.myPlant.CreateMyPlantIntent
import com.shurdev.domain.models.myPlant.MyPlant
import com.shurdev.domain.models.myPlant.MyPlantId
import kotlinx.coroutines.flow.Flow

interface MyPlantsRepository {
    fun getAll(): Flow<List<MyPlant>>

    suspend fun getById(id: MyPlantId): MyPlant

    suspend fun create(data: CreateMyPlantIntent) : Long

    suspend fun delete(
        plantId: MyPlantId,
        plantName: String,
    )
}