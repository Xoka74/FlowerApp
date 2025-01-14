package com.shurdev.domain.models.trade

data class Trade(
    val id: Int? = null,
    val plantToGet: PlantTrade,
    val plantToGive: PlantTrade,
    val authorName: String,
    val contactData: String,
    val city: String,
)
