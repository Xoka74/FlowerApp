package com.shurdev.settings.viewModel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shurdev.domain.models.settings.Settings
import com.shurdev.domain.models.settings.ThemeType
import com.shurdev.settings.R
import com.shurdev.ui_kit.buttons.SingleChoiceDialogButton
import com.shurdev.ui_kit.errors.ErrorView
import com.shurdev.ui_kit.layouts.DefaultScreenLayout
import com.shurdev.ui_kit.loaders.Loader
import com.shurdev.ui_kit.utils.toResString

@Composable
internal fun SettingsRoute(
    onDismiss: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel<SettingsViewModel>(),
) {

    val uiState by viewModel.uiState.collectAsState()

    SettingsScreen(
        onBackInvoked = onDismiss,
        uiState = uiState,
        onThemeSelected = viewModel::updateTheme,
    )
}


@Composable
internal fun SettingsScreen(
    uiState: SettingsUiState,
    onThemeSelected: (ThemeType) -> Unit,
    onBackInvoked: () -> Unit,
) {
    DefaultScreenLayout(
        title = stringResource(com.shurdev.ui_kit.R.string.settings),
        onBackInvoked = onBackInvoked,
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            when (uiState) {
                SettingsErrorState -> ErrorView()
                SettingsLoadingState -> Loader()
                is SettingsLoadedState -> SettingsScreenContent(
                    settings = uiState.settings,
                    onThemeSelected = onThemeSelected,
                )
            }
        }
    }
}

@Composable
internal fun SettingsScreenContent(
    settings: Settings,
    onThemeSelected: (ThemeType) -> Unit,
) {
    Column {
        SingleChoiceDialogButton(
            modifier = Modifier.padding(16.dp),
            title = stringResource(R.string.app_theme),
            items = ThemeType.entries,
            onItemSelected = onThemeSelected,
            itemToString = { it.toResString() },
            selectedItem = settings.themeType,
        )
    }
}