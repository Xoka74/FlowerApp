package com.shurdev.my_plants.screens.details.composables

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun DeletePlantIconButton(
    onConfirm: () -> Unit,
    icon: ImageVector,
    contentDescription: String?,
) {
    var showAlertDialog by remember { mutableStateOf(false) }

    if (showAlertDialog) {
        ConfirmDeletePlantDialog(
            onDismissRequest = {
                showAlertDialog = false
            },
            onConfirmation = {
                showAlertDialog = false
                onConfirm.invoke()
            },
        )
    }

    IconButton(
        onClick = {
            showAlertDialog = true
        },
    ) {
        Icon(icon, contentDescription)
    }
}