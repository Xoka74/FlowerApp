package com.shurdev.onboarding.components

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.shurdev.onboarding.R
import kotlinx.coroutines.delay

@Composable
fun LoaderAnimator(
    modifier: Modifier = Modifier,
    durationMillis: Int,
    onFinishOnboarding: () -> Unit
) {

    val alpha = remember { androidx.compose.animation.core.Animatable(0f) }

    LaunchedEffect(true) {
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = durationMillis,
                easing = EaseInOut
            )
        )

        delay(durationMillis.toLong())
        onFinishOnboarding()
    }

    Image(
        modifier = modifier
            .alpha(alpha.value),
        painter = painterResource(R.drawable.image_hello),
        contentDescription = stringResource(R.string.welcome)
    )

    Text(
        modifier = Modifier
            .alpha(alpha.value),
        text = stringResource(R.string.welcome),
        fontSize = 32.sp
    )
}

@Preview(showBackground = true)
@Composable
fun LoaderAnimatorPreview() {
    LoaderAnimator(
        durationMillis = 3000,
        onFinishOnboarding = {}
    )
}