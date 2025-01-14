package com.shurdev.data.dataSource

import androidx.datastore.core.DataStore
import com.shurdev.data.mappers.toDomainModel
import com.shurdev.data.models.SettingsEntity
import com.shurdev.domain.models.settings.Settings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingsDataSource @Inject constructor(
    private val dataStore: DataStore<SettingsEntity>,
) {
    val settings: Flow<Settings> = dataStore.data.map { it.toDomainModel() }

    suspend fun updateSettings(transform: suspend (SettingsEntity) -> SettingsEntity) {
        dataStore.updateData(transform = transform)
    }
}