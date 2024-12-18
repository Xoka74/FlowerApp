package com.shurdev.ui_kit.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.shurdev.domain.models.plant.ToxicCategory
import com.shurdev.ui_kit.R

@Composable
fun ToxicCategory.toResString(): String {

    val id =  when (this) {
        ToxicCategory.Kids -> R.string.kids
        ToxicCategory.Pets -> R.string.pets
        ToxicCategory.People -> R.string.people
    }

    return stringResource(id)
}