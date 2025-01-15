package com.shurdev.domain.models.trade


data class CreateTradeModel(
    val id: Int,
    val plantToGiveName: String,
    val plantToGetName: String,
    val city: String,
    val description:String,
    val plantToGiveImage: String,
    val contactData: String,
    val authorName: String,
)
