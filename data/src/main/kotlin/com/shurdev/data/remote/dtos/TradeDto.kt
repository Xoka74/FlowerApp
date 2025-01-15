package com.shurdev.data.remote.dtos

data class TradeDto(
    val authorName: String,
    val contactData: String,
    val description: String,
    val expiresAt: String,
    val flowerName: String,
    val id: Int,
    val isActive: Boolean,
    val location: String,
    val photoBase64: String,
    val preferredTrade: String
)