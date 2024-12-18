package com.shurdev.domain.models.trade

import com.shurdev.domain.models.Plant

data class Trade(
    val id: Int? = null,
    val plantToGet: Plant,
    val plantToGive: Plant,
    val authorName: String,
)
