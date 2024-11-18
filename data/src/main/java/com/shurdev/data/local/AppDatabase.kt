package com.shurdev.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [
        MyPlantEntity::class
    ],
)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val NAME = "app_database"
    }
}