package com.shurdev.ui_kit.actions

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.shurdev.ui_kit.R

@Composable
fun SettingsAction(
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick,
    ) {
        val icon = Icons.Default.Settings
        val settings = stringResource(R.string.settings)

        Icon(icon, settings)
    }
}