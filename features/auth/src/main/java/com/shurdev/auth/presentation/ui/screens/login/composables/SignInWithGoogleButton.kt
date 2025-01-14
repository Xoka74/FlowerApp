package com.shurdev.auth.presentation.ui.screens.login.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.shurdev.auth.R
import com.shurdev.ui_kit.buttons.PrimaryButton

@Composable
fun SignInWithGoogleButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isLoading: Boolean = false,
) {
    val signInWithGoogle = stringResource(R.string.signInWithGoogle)

    PrimaryButton(
        modifier = modifier,
        text = signInWithGoogle,
        onClick = onClick,
        icon = painterResource(R.drawable.icon_google_logo),
        isLoading = isLoading,
    )
}
