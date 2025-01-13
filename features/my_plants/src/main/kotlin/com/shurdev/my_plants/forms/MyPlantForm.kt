package com.shurdev.my_plants.forms

import com.shurdev.domain.models.myPlant.PlantOtherInfo
import com.shurdev.domain.models.myPlant.PlantWatering
import com.shurdev.domain.forms.ValidationError
import com.shurdev.ui_kit.viewModel.trackChanges.TrackChangesForm

data class MyPlantForm(
    val name: String = "",
    val imageData: ByteArray? = null,
    val watering: PlantWatering? = null,
    val otherInfo: PlantOtherInfo? = null,
) : TrackChangesForm<MyPlantFormValidationError>() {

    override fun hasChanges(other: TrackChangesForm<MyPlantFormValidationError>) = other != this

    override fun validate(): MyPlantFormValidationError? {
        if (name.isEmpty()) {
            return MyPlantFormValidationError(nameError = ValidationError.Required)
        }

        return null
    }
}