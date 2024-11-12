package com.shurdev.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.shurdev.onboarding.components.LoaderAnimator

@Composable
fun OnboardingRoute(
    onFinishOnboarding: () -> Unit
) {

    OnboardingScreen(
        onFinishOnboarding = onFinishOnboarding
    )
}

@Composable
fun OnboardingScreen(
    onFinishOnboarding: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LoaderAnimator(
            durationMillis = 3000,
            onFinishOnboarding = onFinishOnboarding
        )
    }

}