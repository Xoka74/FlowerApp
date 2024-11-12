package com.shurdev.profile.composables

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.shurdev.ui_kit.actions.SettingsAction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileAppBar(
    onSettingsClick: () -> Unit,
) {
    TopAppBar(
        title = {},
        windowInsets = WindowInsets.Companion.ime,
        actions = {
            SettingsAction(
                onClick = onSettingsClick,
            )
        }
    )
}