package com.shurdev.uiKit.actions

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.shurdev.ui_kit.R

@Composable
fun FavoriteAction(
    onClick: () -> Unit,
    isActive: Boolean,
    modifier: Modifier = Modifier,
) {
    IconButton(
        modifier = modifier,
        onClick = onClick,
    ) {
        val icon = if (isActive) Icons.Default.Favorite else Icons.Default.FavoriteBorder
        val addToFavorite = stringResource(R.string.add_to_favorite)

        Icon(icon, addToFavorite)
    }
}
