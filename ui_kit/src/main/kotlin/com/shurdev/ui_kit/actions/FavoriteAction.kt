package com.shurdev.ui_kit.actions

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.shurdev.ui_kit.R

@Composable
fun FavoriteAction(
    onClick: () -> Unit,
    isActive: Boolean,
) {
    IconButton(
        onClick = onClick,
    ) {
        val icon = if (isActive) Icons.Default.Favorite else Icons.Default.FavoriteBorder
        val addToFavorite = stringResource(R.string.add_to_favorite)

        Icon(icon, addToFavorite)
    }
}