package com.shurdev.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shurdev.domain.models.myPlant.PlantOtherInfo
import com.shurdev.domain.models.myPlant.PlantWatering
import com.shurdev.domain.models.plant.PlantId

@Entity
data class MyPlantEntity(
    @PrimaryKey(autoGenerate = true) val id: PlantId = 0,
    val name: String,
    val imageData: ByteArray?,
    @Embedded
    val plantWatering: PlantWatering? = null,
    @Embedded
    val otherInfo: PlantOtherInfo? = null,
)