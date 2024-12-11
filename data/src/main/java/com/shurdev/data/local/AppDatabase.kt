package com.shurdev.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shurdev.data.converters.ToxicCategoryHashSetConverter
import com.shurdev.data.converters.ListConverter
import com.shurdev.data.converters.LocalDateTimeConverter
import com.shurdev.data.daos.MyPlantsDao
import com.shurdev.data.daos.SurveyResultsDao
import com.shurdev.data.entities.MyPlantEntity
import com.shurdev.data.entities.SurveyResultsEntity

@Database(
    version = 1,
    entities = [
        MyPlantEntity::class,
        SurveyResultsEntity::class
    ],
    exportSchema = false,
)
@TypeConverters(
    ListConverter::class,
    LocalDateTimeConverter::class,
    ToxicCategoryHashSetConverter::class,
)
abstract class AppDatabase : RoomDatabase() {

    abstract val surveyResultsDao: SurveyResultsDao

    abstract val myPlantsDao: MyPlantsDao

    companion object {
        const val NAME = "app_database"
    }
}