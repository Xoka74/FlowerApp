package com.shurdev.domain.repositories

import com.shurdev.domain.models.CreateMyPlantIntent
import com.shurdev.domain.models.MyPlant
import com.shurdev.domain.models.MyPlantId
import kotlinx.coroutines.flow.Flow

interface MyPlantsRepository {
    fun getAll(): Flow<List<MyPlant>>

    suspend fun getById(id: MyPlantId) : MyPlant

    suspend fun create(data: CreateMyPlantIntent)

    suspend fun delete(plantId: MyPlantId)
}