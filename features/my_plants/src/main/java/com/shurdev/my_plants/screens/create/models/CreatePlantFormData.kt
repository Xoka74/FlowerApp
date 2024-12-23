package com.shurdev.my_plants.screens.create.models

import com.shurdev.domain.models.myPlant.PlantOtherInfo
import com.shurdev.domain.models.myPlant.PlantWatering
import com.shurdev.ui_kit.viewModel.trackChanges.TrackChangesForm

data class CreatePlantForm(
    val name: String = "",
    val imageData: ByteArray? = null,
    val watering: PlantWatering? = null,
    val otherInfo: PlantOtherInfo? = null,
) : TrackChangesForm<CreatePlantFormValidationError>() {

    override fun hasChanges(other: TrackChangesForm<CreatePlantFormValidationError>) = other != this

    override fun validate(): CreatePlantFormValidationError? {
        if (name.isEmpty()) {
            return CreatePlantFormValidationError(name = "Обязательное поле")
        }

        return null
    }
}