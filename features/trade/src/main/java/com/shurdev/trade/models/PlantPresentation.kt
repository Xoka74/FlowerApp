package com.shurdev.trade.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlantPresentation(
    val id: Int? = null,
    val name: String,
    val description: String,
    val imageLink: String,
) : Parcelable
