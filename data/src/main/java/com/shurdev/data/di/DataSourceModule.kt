package com.shurdev.data.di

import android.content.Context
import android.content.SharedPreferences
import com.shurdev.data.dataSource.SettingsDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("preferences_name", Context.MODE_PRIVATE) // TODO change
    }

    @Provides
    @Singleton
    fun provideSettingsDataSource(sharedPreferences: SharedPreferences): SettingsDataSource {
        return SettingsDataSource(sharedPreferences)
    }
}