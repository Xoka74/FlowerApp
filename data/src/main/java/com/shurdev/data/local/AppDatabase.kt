package com.shurdev.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shurdev.data.entities.MyPlantEntity
import com.shurdev.data.daos.MyPlantsDao

@Database(
    version = 2,
    entities = [
        MyPlantEntity::class
    ],
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun myPlantsDao(): MyPlantsDao

    companion object {
        const val NAME = "app_database"
    }
}