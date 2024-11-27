package com.shurdev.di

import com.shurdev.data.daos.MyPlantsDao
import com.shurdev.data.local.AppDatabase
import com.shurdev.data.local.dao.SurveyResultsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DaoModule {

    @Provides
    @Singleton
    fun provideMyPlantDao(appDatabase: AppDatabase): MyPlantsDao {
        return appDatabase.myPlantsDao()
    }

    @Provides
    @Singleton
    fun provideSurveyResultsDao(database: AppDatabase): SurveyResultsDao {
        return database.surveyResultsDao
    }
}