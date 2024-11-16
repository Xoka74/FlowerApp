package com.shurdev.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MyPlantEntity(
    @PrimaryKey val id: Int?,
    val name: String,
)