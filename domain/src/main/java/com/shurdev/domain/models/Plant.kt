package com.shurdev.domain.models

typealias PlantId = Int

data class Plant(
    val id: PlantId? = null,
    val name: String,
    val description: String,
    val imageLink: String,
)
