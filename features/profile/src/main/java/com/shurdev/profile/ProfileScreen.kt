package com.shurdev.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shurdev.domain.models.user.User
import com.shurdev.profile.composables.ProfileHeader
import com.shurdev.profile.composables.ProfileMenu
import com.shurdev.profile.viewModel.ProfileErrorState
import com.shurdev.profile.viewModel.ProfileLoadedState
import com.shurdev.profile.viewModel.ProfileLoadingState
import com.shurdev.profile.viewModel.ProfileUiState
import com.shurdev.profile.viewModel.ProfileViewModel
import com.shurdev.ui_kit.actions.SettingsAction
import com.shurdev.ui_kit.bars.TopBar
import com.shurdev.ui_kit.errors.ErrorView
import com.shurdev.ui_kit.loaders.Loader

@Composable
internal fun ProfileRoute(
    onTakeSurveyClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onRecommendedPlantsClick: () -> Unit,
) {

    val viewModel = hiltViewModel<ProfileViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    ProfileScreen(
        uiState = uiState,
        onLogoutClick = viewModel::logout,
        onTakeSurveyClick = onTakeSurveyClick,
        onSettingsClick = onSettingsClick,
        onRecommendedPlantsClick = onRecommendedPlantsClick
    )
}

@Composable
internal fun ProfileScreen(
    uiState: ProfileUiState,
    onLogoutClick: () -> Unit = {},
    onTakeSurveyClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onRecommendedPlantsClick: () -> Unit,
) {
    when (uiState) {
        ProfileErrorState -> ErrorView()
        ProfileLoadingState -> Loader()
        is ProfileLoadedState -> ProfileScreenContent(
            user = uiState.user,
            onLogoutClick = onLogoutClick,
            onTakeSurveyClick = onTakeSurveyClick,
            onSettingsClick = onSettingsClick,
            onRecommendedPlantsClick = onRecommendedPlantsClick
        )
    }
}

@Composable
internal fun ProfileScreenContent(
    user: User,
    onLogoutClick: () -> Unit = {},
    onTakeSurveyClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onRecommendedPlantsClick: () -> Unit,
) {
    Column {
        Spacer(Modifier.height(20.dp))

        TopBar(
            modifier = Modifier.padding(horizontal = 16.dp),
            title = stringResource(R.string.profile),
            actions = {
                SettingsAction(
                    onClick = onSettingsClick,
                )
            }
        )

        ProfileHeader(user)

        Spacer(Modifier.height(20.dp))

        ProfileMenu(
            onTakeSurveyClick = onTakeSurveyClick,
            onLogoutClick = onLogoutClick,
            onRecommendedPlantsClick = onRecommendedPlantsClick
        )
    }
}