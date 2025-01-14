package com.shurdev.my_plants.forms

import com.shurdev.domain.models.myPlant.PlantOtherInfo
import com.shurdev.domain.models.myPlant.PlantWatering
import com.shurdev.domain.models.plant.Illumination
import com.shurdev.domain.models.plant.ToxicCategory
import com.shurdev.domain.models.plant.WateringFrequency
import com.shurdev.ui_kit.viewModel.trackChanges.TrackChangesFormViewModel
import java.time.LocalDateTime

abstract class MyPlantFormViewModel(
    initialData: MyPlantForm,
) : TrackChangesFormViewModel<MyPlantFormValidationError, MyPlantForm>(initialData) {
    fun updateName(value: String) = updateFormData { it.copy(name = value) }

    fun updateWateringSelection(selected: Boolean) {
        val newData = if (selected) PlantWatering(
            lastWateringTime = LocalDateTime.now()
        ) else null
        updateFormData { it.copy(watering = newData) }
    }

    fun updateOtherInfoSelection(selected: Boolean) {
        val newData = if (selected) PlantOtherInfo() else null
        updateFormData { it.copy(otherInfo = newData) }
    }

    fun removeToxicCategory(value: ToxicCategory) {
        val items = formData.otherInfo?.toxicCategories ?: return
        val newItems = items.filter { it != value }.toHashSet()

        updateFormData {
            it.copy(
                otherInfo = it.otherInfo?.copy(
                    toxicCategories = newItems
                )
            )
        }
    }

    fun addToxicCategory(value: ToxicCategory) {
        val items = formData.otherInfo?.toxicCategories ?: hashSetOf()

        val newItems = items.toMutableSet().apply {
            add(value)
        }

        updateFormData {
            it.copy(
                otherInfo = it.otherInfo?.copy(
                    toxicCategories = newItems.toHashSet()
                )
            )
        }
    }

    fun updateIllumination(value: Illumination) = updateFormData {
        it.copy(
            otherInfo = it.otherInfo?.copy(
                illumination = value
            )
        )
    }

    fun updateWateringFrequency(value: WateringFrequency) = updateFormData {
        it.copy(
            watering = it.watering?.copy(
                frequency = value,
            )
        )
    }

    fun updateLastWateringTime(value: LocalDateTime) = updateFormData {
        it.copy(
            watering = it.watering?.copy(
                lastWateringTime = value,
            )
        )
    }

    fun updateImage(imageData: ByteArray?) {
        updateFormData { it.copy(imageData = imageData) }
    }
}