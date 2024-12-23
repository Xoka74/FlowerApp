package com.shurdev.my_plants.screens.create.viewModel

import androidx.lifecycle.viewModelScope
import com.shurdev.domain.models.myPlant.CreateMyPlantIntent
import com.shurdev.domain.models.myPlant.PlantOtherInfo
import com.shurdev.domain.models.myPlant.PlantWatering
import com.shurdev.domain.models.plant.Illumination
import com.shurdev.domain.models.plant.ToxicCategory
import com.shurdev.domain.models.plant.WateringFrequency
import com.shurdev.domain.usecases.CreateMyPlantUseCase
import com.shurdev.my_plants.screens.create.models.CreatePlantForm
import com.shurdev.my_plants.screens.create.models.CreatePlantFormValidationError
import com.shurdev.ui_kit.viewModel.form.FormSubmissionErrorState
import com.shurdev.ui_kit.viewModel.form.FormSubmittedState
import com.shurdev.ui_kit.viewModel.form.FormSubmittingState
import com.shurdev.ui_kit.viewModel.trackChanges.TrackChangesFormViewModel
import com.shurdev.utils.runSuspendCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class MyPlantCreateViewModel @Inject constructor(
    private val createMyPlantUseCase: CreateMyPlantUseCase,
) : TrackChangesFormViewModel<CreatePlantFormValidationError, CreatePlantForm>(
    initialData = CreatePlantForm()
) {
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

    override fun sendForm() {
        viewModelScope.launch {
            updateUiState { FormSubmittingState }

            val intent = CreateMyPlantIntent(
                name = formData.name,
                plantWatering = formData.watering,
                otherInfo = formData.otherInfo,
                imageData = formData.imageData
            )

            runSuspendCatching {
                createMyPlantUseCase(intent)
            }.onSuccess {
                saveChanges()
                updateUiState { FormSubmittedState(Unit) }
            }.onFailure {
                updateUiState { FormSubmissionErrorState }
            }
        }
    }
}