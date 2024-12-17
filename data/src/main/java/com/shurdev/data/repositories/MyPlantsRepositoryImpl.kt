package com.shurdev.data.repositories

import com.shurdev.data.daos.MyPlantsDao
import com.shurdev.data.entities.MyPlantEntity
import com.shurdev.data.mappers.toDomainModel
import com.shurdev.domain.models.CreateMyPlantIntent
import com.shurdev.domain.models.MyPlant
import com.shurdev.domain.models.MyPlantId
import com.shurdev.domain.repositories.MyPlantsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MyPlantsRepositoryImpl @Inject constructor(
    private val myPlantsDao: MyPlantsDao,
) : MyPlantsRepository {
    override fun getAll(): Flow<List<MyPlant>> =
        myPlantsDao.getAll().map { plants -> plants.map { it.toDomainModel() } }

    override suspend fun getById(id: MyPlantId): MyPlant {
        return withContext(Dispatchers.IO) {
            myPlantsDao.getById(id).toDomainModel()
        }
    }

    override suspend fun create(data: CreateMyPlantIntent) {
        return withContext(Dispatchers.IO) {
            myPlantsDao.insert(
                MyPlantEntity(
                    name = data.name,
                    imageData = data.imageData
                )
            )
        }
    }

    override suspend fun delete(plantId: MyPlantId) {
        return withContext(Dispatchers.IO) {
            myPlantsDao.delete(plantId)
        }
    }
}