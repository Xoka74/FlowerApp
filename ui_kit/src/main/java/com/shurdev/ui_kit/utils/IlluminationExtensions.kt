package com.shurdev.ui_kit.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.shurdev.domain.models.plant.Illumination
import com.shurdev.ui_kit.R

@Composable
fun Illumination.toResString(): String {
    val id =  when (this) {
        Illumination.Bright -> R.string.bright
        Illumination.PartialShade -> R.string.partial_shade
        Illumination.Average -> R.string.average
    }

    return stringResource(id)
}