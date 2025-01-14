package com.shurdev.domain.repositories

import com.shurdev.domain.models.settings.Settings
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    val settings: Flow<Settings>

    suspend fun updateSettings(transform: suspend (Settings) -> Settings)
}