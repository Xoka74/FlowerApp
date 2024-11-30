package com.shurdev.ui_kit.layouts

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.shurdev.ui_kit.dialogs.ConfirmLeaveDialog

@Composable
fun ConfirmLeaveScreenLayout(
    modifier: Modifier = Modifier,
    onBackInvoked: (() -> Unit)? = null,
    actions: (@Composable RowScope.() -> Unit)? = null,
    showConfirmLeave: (() -> Boolean)? = null,
    content: @Composable () -> Unit,
) {
    var showAlertDialog by remember { mutableStateOf(false) }

    if (showAlertDialog) {
        ConfirmLeaveDialog(
            onDismissRequest = {
                showAlertDialog = false
            },
            onConfirmation = {
                showAlertDialog = false
                onBackInvoked?.invoke()
            },
        )
    }

    DefaultScreenLayout(
        modifier = modifier,
        onBackInvoked = {
            if (showConfirmLeave?.invoke() == true) {
                showAlertDialog = true
            } else {
                onBackInvoked?.invoke()
            }
        },
        actions = actions,
        content = content,
    )
}