package com.shurdev.data.repositories

import com.shurdev.data.dataSource.SettingsDataSource
import com.shurdev.data.mappers.toDomainModel
import com.shurdev.data.mappers.toEntity
import com.shurdev.domain.models.settings.Settings
import com.shurdev.domain.repositories.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val settingsDataSource: SettingsDataSource,
) : SettingsRepository {
    override val settings: Flow<Settings> = settingsDataSource.settings

    override suspend fun updateSettings(transform: suspend (Settings) -> Settings) {
        return settingsDataSource.updateSettings {
            transform(it.toDomainModel()).toEntity()
        }
    }
}