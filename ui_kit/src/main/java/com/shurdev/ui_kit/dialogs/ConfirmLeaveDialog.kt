package com.shurdev.ui_kit.dialogs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.shurdev.ui_kit.R
import com.shurdev.ui_kit.dialogs.base.BaseConfirmDialog

@Composable
fun ConfirmLeaveDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    val confirmActionTitle = stringResource(R.string.are_you_sure_you_want_to_leave)
    val changesWontBeSavedString = stringResource(R.string.changes_wont_be_saved)
    val continueText = stringResource(R.string.continue_text)
    val cancelText = stringResource(R.string.cancel)

    BaseConfirmDialog(
        icon = Icons.Default.Info,
        title = confirmActionTitle,
        text = changesWontBeSavedString,
        confirmText = continueText,
        dismissText = cancelText,
        onDismissRequest = onDismissRequest,
        onConfirmation = onConfirmation,
    )
}