package com.shurdev.data.di

import android.content.Context
import androidx.room.Room
import com.shurdev.data.local.AppDatabase
import com.shurdev.data.local.dao.SurveyResultsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.NAME).build()
    }

    @Provides
    @Singleton
    fun provideSurveyResultsDao(database: AppDatabase): SurveyResultsDao {
        return database.surveyResultsDao
    }
}