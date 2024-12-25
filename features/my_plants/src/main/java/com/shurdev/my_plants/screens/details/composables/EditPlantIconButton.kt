package com.shurdev.my_plants.screens.details.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable

@Composable
fun EditIconButton(
    onTap: () -> Unit,
) {
    IconButton(
        onClick = onTap,
    ) {
        Icon(Icons.Default.Edit, null)
    }
}