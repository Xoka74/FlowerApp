package com.shurdev.data.models

import com.shurdev.domain.models.settings.ThemeType
import kotlinx.serialization.Serializable

@Serializable
data class SettingsEntity(
    val themeType: ThemeType = ThemeType.System,
)