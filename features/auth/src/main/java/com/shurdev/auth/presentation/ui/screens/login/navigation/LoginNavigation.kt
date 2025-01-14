package com.shurdev.auth.presentation.ui.screens.login.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.shurdev.auth.presentation.ui.screens.login.LoginRoute
import kotlinx.serialization.Serializable

@Serializable
object LoginRoute

fun NavController.navigateToLogin() = navigate(LoginRoute)

fun NavGraphBuilder.loginScreen(
    onSuccessLogin: () -> Unit = {},
) {
    composable<LoginRoute> {
        LoginRoute(
            onSuccessLogin = onSuccessLogin,
        )
    }
}