package com.shurdev.flowerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.shurdev.domain.models.settings.ThemeType
import com.shurdev.flowerapp.presentation.FlowerApp
import com.shurdev.flowerapp.presentation.composables.NotificationPermissionHandler
import com.shurdev.settings.viewModel.SettingsLoadedState
import com.shurdev.settings.viewModel.SettingsViewModel
import com.shurdev.ui_kit.theme.FlowerAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val settingsViewModel = hiltViewModel<SettingsViewModel>()

            val settingsUiState by settingsViewModel.uiState.collectAsState()

            val themeType = (settingsUiState as? SettingsLoadedState)?.settings?.themeType

            val isDarkTheme = when (themeType) {
                ThemeType.Light -> false
                ThemeType.Dark -> true
                else -> isSystemInDarkTheme()
            }

            FlowerAppTheme(
                darkTheme = isDarkTheme
            ) {
                NotificationPermissionHandler {
                    FlowerApp()
                }
            }
        }
    }
}