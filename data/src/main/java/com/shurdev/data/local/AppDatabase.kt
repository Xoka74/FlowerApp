package com.shurdev.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shurdev.data.local.converters.ListConverter
import com.shurdev.data.local.dao.SurveyResultsDao
import com.shurdev.data.local.entity.MyPlantEntity
import com.shurdev.data.local.entity.SurveyResultsEntity

@Database(
    version = 1,
    entities = [
        MyPlantEntity::class,
        SurveyResultsEntity::class
    ],
)
@TypeConverters(ListConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract val surveyResultsDao: SurveyResultsDao

    companion object {
        const val NAME = "app_database"
    }
}