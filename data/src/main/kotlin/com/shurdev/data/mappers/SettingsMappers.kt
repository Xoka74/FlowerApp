package com.shurdev.data.mappers

import com.shurdev.data.models.SettingsEntity
import com.shurdev.domain.models.settings.Settings

fun SettingsEntity.toDomainModel(): Settings {
    return Settings(
        themeType = themeType,
    )
}

fun Settings.toEntity(): SettingsEntity {
    return SettingsEntity(
        themeType = themeType,
    )
}