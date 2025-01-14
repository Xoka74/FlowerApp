package com.shurdev.flowerapp.di.modules

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.shurdev.data.dataSource.SettingsDataSource
import com.shurdev.data.dataSource.StartupDataSource
import com.shurdev.data.models.SettingsEntity
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
    fun provideStartupDataSource(dataStore: DataStore<Preferences>): StartupDataSource {
        return StartupDataSource(dataStore)
    }

    @Provides
    @Singleton
    fun provideSettingsDataSource(dataStore: DataStore<SettingsEntity>): SettingsDataSource {
        return SettingsDataSource(dataStore)
    }
}