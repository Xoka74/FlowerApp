package com.shurdev.profile.composables.dialogs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.shurdev.profile.R
import com.shurdev.ui_kit.dialogs.base.BaseConfirmDialog
import com.shurdev.ui_kit.R as uiKitRes

@Composable
fun ConfirmLogoutDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    val confirmActionTitle = stringResource(R.string.are_you_sure_you_want_to_logout)
    val continueText = stringResource(uiKitRes.string.continue_text)
    val cancelText = stringResource(uiKitRes.string.cancel)

    BaseConfirmDialog(
        icon = Icons.AutoMirrored.Filled.ExitToApp,
        title = confirmActionTitle,
        confirmText = continueText,
        dismissText = cancelText,
        onDismissRequest = onDismissRequest,
        onConfirmation = onConfirmation,
    )
}