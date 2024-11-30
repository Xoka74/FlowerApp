package com.shurdev.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shurdev.domain.models.PlantId

@Entity
data class MyPlantEntity(
    @PrimaryKey(autoGenerate = true) val id: PlantId = 0,
    val name: String,
)