package com.shurdev.domain.models

data class CreateMyPlantIntent(
    val name: String,
    val imageData: ByteArray?
)