package com.shurdev.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.shurdev.domain.models.user.User
import com.shurdev.profile.composables.ProfileAction
import com.shurdev.profile.composables.ProfileOption
import com.shurdev.profile.mocks.USER
import com.shurdev.profile.viewModel.ProfileErrorState
import com.shurdev.profile.viewModel.ProfileLoadedState
import com.shurdev.profile.viewModel.ProfileLoadingState
import com.shurdev.profile.viewModel.ProfileUiState
import com.shurdev.profile.viewModel.ProfileViewModel
import com.shurdev.ui_kit.R
import com.shurdev.ui_kit.actions.SettingsAction
import com.shurdev.ui_kit.errors.ErrorView
import com.shurdev.ui_kit.loaders.Loader

@Composable
internal fun ProfileRoute() {

    val viewModel = hiltViewModel<ProfileViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    ProfileScreen(
        uiState = uiState,
        onLogoutClick = viewModel::logout,
    )
}

@Composable
internal fun ProfileScreen(
    uiState: ProfileUiState,
    onLogoutClick: () -> Unit = {},
) {
    when (uiState) {
        ProfileErrorState -> ErrorView()
        ProfileLoadingState -> Loader()
        is ProfileLoadedState -> ProfileScreenContent(
            user = uiState.user,
            onLogoutClick = onLogoutClick,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ProfileScreenContent(
    user: User,
    onLogoutClick: () -> Unit = {},
) {
    Column(Modifier.padding(horizontal = 20.dp)) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TopAppBar(
                title = {},
                windowInsets = WindowInsets.Companion.ime,
                actions = {
                    // TODO: Implement me
                    SettingsAction(
                        onClick = {}
                    )
                }
            )

            AsyncImage(
                modifier = Modifier
                    .clip(CircleShape),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(user.imageLink)
                    .build(),
                contentDescription = "Your Plant",
                contentScale = ContentScale.Crop
            )

            Spacer(Modifier.height(8.dp))

            Text(user.name)
        }

        Spacer(Modifier.height(20.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            // TODO: To navigation items similar to BottomNavigationItems
            val options = listOf("Избранное", "О приложении")

            for (option in options) {
                ProfileOption(option)
            }

            val logoutString = stringResource(R.string.logout)

            ProfileAction(
                text = logoutString,
                onClick = onLogoutClick,
            )
        }
    }
}

@Preview
@Composable
fun PreviewProfileScreenContent() {
    ProfileScreenContent(
        user = USER
    )
}