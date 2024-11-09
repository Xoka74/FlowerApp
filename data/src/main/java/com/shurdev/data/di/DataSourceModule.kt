package com.shurdev.data.di

import android.content.SharedPreferences
import com.shurdev.data.dataSource.SettingsDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {
    @Provides
    @Singleton
    fun provideSettingsDataSource(sharedPreferences: SharedPreferences): SettingsDataSource {
        return SettingsDataSource(sharedPreferences)
    }
}