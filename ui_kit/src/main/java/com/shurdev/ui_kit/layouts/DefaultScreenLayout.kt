package com.shurdev.ui_kit.layouts

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.shurdev.ui_kit.buttons.BackButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultScreenLayout(
    modifier: Modifier = Modifier,
    onBackInvoked: (() -> Unit)? = null,
    actions: (@Composable RowScope.() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    Column {
        if (actions != null || onBackInvoked != null) {
            TopAppBar(
                title = {},
                windowInsets = WindowInsets(0, 0, 0, 0),
                navigationIcon = { if (onBackInvoked != null) BackButton(onBackInvoked) },
                actions = actions ?: {}
            )
        }
        Box(modifier) {
            content()
        }
    }

    if (onBackInvoked != null) {
        BackHandler(onBack = onBackInvoked)
    }
}