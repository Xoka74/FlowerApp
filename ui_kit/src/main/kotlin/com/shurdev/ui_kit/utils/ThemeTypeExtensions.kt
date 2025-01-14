package com.shurdev.ui_kit.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.shurdev.domain.models.settings.ThemeType
import com.shurdev.ui_kit.R

@Composable
fun ThemeType.toResString(): String {
    val id = when (this) {
        ThemeType.System -> R.string.system
        ThemeType.Light -> R.string.light
        ThemeType.Dark -> R.string.dark
    }

    return stringResource(id)
}