package com.shurdev.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shurdev.domain.models.user.User
import com.shurdev.profile.composables.ProfileAppBar
import com.shurdev.profile.composables.ProfileHeader
import com.shurdev.profile.composables.ProfileMenu
import com.shurdev.profile.viewModel.ProfileErrorState
import com.shurdev.profile.viewModel.ProfileLoadedState
import com.shurdev.profile.viewModel.ProfileLoadingState
import com.shurdev.profile.viewModel.ProfileUiState
import com.shurdev.profile.viewModel.ProfileViewModel
import com.shurdev.ui_kit.errors.ErrorView
import com.shurdev.ui_kit.loaders.Loader

@Composable
internal fun ProfileRoute(
    onTakeSurveyClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
) {

    val viewModel = hiltViewModel<ProfileViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    ProfileScreen(
        uiState = uiState,
        onLogoutClick = viewModel::logout,
        onTakeSurveyClick = onTakeSurveyClick,
        onSettingsClick = onSettingsClick,
    )
}

@Composable
internal fun ProfileScreen(
    uiState: ProfileUiState,
    onLogoutClick: () -> Unit = {},
    onTakeSurveyClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
) {
    when (uiState) {
        ProfileErrorState -> ErrorView()
        ProfileLoadingState -> Loader()
        is ProfileLoadedState -> ProfileScreenContent(
            user = uiState.user,
            onLogoutClick = onLogoutClick,
            onTakeSurveyClick = onTakeSurveyClick,
            onSettingsClick = onSettingsClick,
        )
    }
}

@Composable
internal fun ProfileScreenContent(
    user: User,
    onLogoutClick: () -> Unit = {},
    onTakeSurveyClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
) {
    Column {
        ProfileAppBar(
            onSettingsClick = onSettingsClick
        )

        ProfileHeader(user)

        Spacer(Modifier.height(20.dp))

        ProfileMenu(
            onTakeSurveyClick = onTakeSurveyClick,
            onLogoutClick = onLogoutClick,
        )
    }
}