package com.shurdev.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.shurdev.data.entities.MyPlantEntity
import com.shurdev.domain.models.myPlant.MyPlantId
import kotlinx.coroutines.flow.Flow

@Dao
interface MyPlantsDao {
    @Query("SELECT * FROM MyPlantEntity")
    fun getAll(): Flow<List<MyPlantEntity>>

    @Query("SELECT * FROM MyPlantEntity WHERE id = :id")
    suspend fun getById(id: MyPlantId): MyPlantEntity

    @Insert
    suspend fun insert(plant: MyPlantEntity) : Long

    @Query("DELETE FROM MyPlantEntity WHERE id = :id")
    suspend fun delete(id: MyPlantId)
}