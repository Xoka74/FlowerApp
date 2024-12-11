package com.shurdev.data.repositories

import com.shurdev.data.daos.MyPlantsDao
import com.shurdev.data.entities.MyPlantEntity
import com.shurdev.data.mappers.toDomainModel
import com.shurdev.domain.models.myPlant.CreateMyPlantIntent
import com.shurdev.domain.models.myPlant.MyPlant
import com.shurdev.domain.models.myPlant.MyPlantId
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

    override suspend fun create(data: CreateMyPlantIntent): Long {
        return withContext(Dispatchers.IO) {
            myPlantsDao.insert(
                MyPlantEntity(
                    name = data.name,
                    plantWatering = data.plantWatering,
                    otherInfo = data.otherInfo,
                )
            )
        }
    }

    override suspend fun delete(
        plantId: MyPlantId,
        plantName: String,
    ) {
        return withContext(Dispatchers.IO) {
            myPlantsDao.delete(plantId)
        }
    }
}