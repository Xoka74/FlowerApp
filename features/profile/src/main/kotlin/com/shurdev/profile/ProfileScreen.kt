package com.shurdev.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shurdev.profile.composables.ProfileHeader
import com.shurdev.profile.composables.ProfileMenu
import com.shurdev.profile.viewModel.ProfileErrorState
import com.shurdev.profile.viewModel.ProfileLoadedState
import com.shurdev.profile.viewModel.ProfileLoadingState
import com.shurdev.profile.viewModel.ProfileUiState
import com.shurdev.profile.viewModel.ProfileViewModel
import com.shurdev.ui_kit.actions.SettingsAction
import com.shurdev.ui_kit.buttons.PrimaryButton
import com.shurdev.ui_kit.errors.ErrorView
import com.shurdev.ui_kit.layouts.DefaultScreenLayout

@Composable
internal fun ProfileRoute(
    onTakeSurveyClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onRecommendedPlantsClick: () -> Unit,
    onTradeClick: () -> Unit = {},
) {

    val viewModel = hiltViewModel<ProfileViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    ProfileScreen(
        uiState = uiState,
        onLogoutClick = viewModel::logout,
        onTakeSurveyClick = onTakeSurveyClick,
        onSettingsClick = onSettingsClick,
        onRecommendedPlantsClick = onRecommendedPlantsClick,
        onTradeClick = onTradeClick,
        onTryAgain = viewModel::loadProfile,
    )
}

@Composable
internal fun ProfileScreen(
    uiState: ProfileUiState,
    onLogoutClick: () -> Unit = {},
    onTakeSurveyClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onRecommendedPlantsClick: () -> Unit = {},
    onTradeClick: () -> Unit = {},
    onTryAgain: () -> Unit = {},
) {
    DefaultScreenLayout(
        title = stringResource(R.string.profile),
        actions = {
            SettingsAction(
                onClick = onSettingsClick,
            )
        }
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                when (uiState) {
                    ProfileLoadingState -> CircularProgressIndicator()
                    is ProfileLoadedState -> ProfileHeader(user = uiState.user)
                    ProfileErrorState -> {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            ErrorView()

                            Spacer(Modifier.height(8.dp))

                            PrimaryButton(
                                text = stringResource(com.shurdev.ui_kit.R.string.try_again),
                                onClick = onTryAgain,
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(20.dp))

            ProfileMenu(
                onTakeSurveyClick = onTakeSurveyClick,
                onLogoutClick = onLogoutClick,
                onRecommendedPlantsClick = onRecommendedPlantsClick,
                onTradeClick = onTradeClick
            )
        }
    }
}