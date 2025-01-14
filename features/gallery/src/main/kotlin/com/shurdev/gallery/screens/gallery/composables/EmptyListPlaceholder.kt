package com.shurdev.gallery.screens.gallery.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.shurdev.ui_kit.R
import com.shurdev.ui_kit.layouts.Center

@Composable
fun EmptyListPlaceholder() {
    val nothingFoundString = stringResource(R.string.nothing_found)

    Center {
        Icon(
            Icons.Filled.Search,
            nothingFoundString,
        )

        Text(nothingFoundString)
    }
}