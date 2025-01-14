package com.shurdev.ui_kit.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.shurdev.domain.models.plant.SortType
import com.shurdev.ui_kit.R

@Composable
fun SortType.toResString(): String {
    val id = when (this) {
        SortType.ByNameAscending -> R.string.by_name_ascending
        SortType.ByNameDescending -> R.string.by_name_descending
        SortType.BySizeAscending -> R.string.by_size_ascending
        SortType.BySizeDescending -> R.string.by_size_descending
    }

    return stringResource(id)
}