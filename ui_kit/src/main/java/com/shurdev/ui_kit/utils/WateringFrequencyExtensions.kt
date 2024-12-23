package com.shurdev.ui_kit.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.shurdev.domain.models.plant.WateringFrequency
import com.shurdev.ui_kit.R

@Composable
fun WateringFrequency.toResString(): String {
    val id =  when (this) {
        WateringFrequency.OnceAWeek -> R.string.once_a_week
        WateringFrequency.TwiceAWeek -> R.string.twice_a_week
        WateringFrequency.OnceEveryTwoWeeks -> R.string.once_every_two_weeks
        WateringFrequency.OnceAMonth -> R.string.once_a_month
    }

    return stringResource(id)
}