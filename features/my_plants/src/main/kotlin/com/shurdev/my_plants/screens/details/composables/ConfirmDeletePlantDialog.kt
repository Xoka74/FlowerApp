package com.shurdev.my_plants.screens.details.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.shurdev.ui_kit.R
import com.shurdev.ui_kit.dialogs.base.BaseConfirmDialog

@Composable
fun ConfirmDeletePlantDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    val confirmActionTitle = stringResource(R.string.are_you_sure_you_want_to_delete_plant)
    val changesWontBeSavedString = stringResource(R.string.you_will_miss_it_forever)
    val confirmText = stringResource(R.string.delete)
    val dismissText = stringResource(R.string.keep)

    BaseConfirmDialog(
        icon = Icons.Default.Info,
        title = confirmActionTitle,
        text = changesWontBeSavedString,
        confirmText = confirmText,
        dismissText = dismissText,
        onDismissRequest = onDismissRequest,
        onConfirmation = onConfirmation,
    )
}