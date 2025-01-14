package com.shurdev.gallery.screens.filters.viewModel

import com.shurdev.domain.models.plant.Illumination
import com.shurdev.domain.models.plant.PlantFilters
import com.shurdev.domain.models.plant.ToxicCategory
import com.shurdev.domain.models.plant.WateringFrequency
import com.shurdev.ui_kit.viewModel.base.BaseViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel(assistedFactory = FiltersViewModel.ViewModelFactory::class)
class FiltersViewModel @AssistedInject constructor(
    @Assisted private val filters: PlantFilters,
) : BaseViewModel<PlantFilters>(filters) {

    fun resetFilters() {
        updateUiState {
            PlantFilters()
        }
    }

    fun removeToxicCategory(value: ToxicCategory) {
        val items = uiState.value.toxicCategories
        val newItems = items.filter { it != value }.toHashSet()

        updateUiState {
            it.copy(
                toxicCategories = newItems
            )
        }
    }

    fun addToxicCategory(value: ToxicCategory) {
        val items = uiState.value.toxicCategories

        val newItems = items.toMutableSet().apply {
            add(value)
        }

        updateUiState {
            it.copy(
                toxicCategories = newItems.toHashSet()
            )
        }
    }

    fun removeIllumination(value: Illumination) {
        val items = uiState.value.illuminations
        val newItems = items.filter { it != value }.toHashSet()

        updateUiState {
            it.copy(
                illuminations = newItems
            )
        }
    }

    fun addIllumination(value: Illumination) {
        val items = uiState.value.illuminations

        val newItems = items.toMutableSet().apply {
            add(value)
        }

        updateUiState {
            it.copy(
                illuminations = newItems.toHashSet()
            )
        }
    }


    fun removeWateringFrequency(value: WateringFrequency) {
        val items = uiState.value.wateringFrequencies
        val newItems = items.filter { it != value }.toHashSet()

        updateUiState {
            it.copy(
                wateringFrequencies = newItems
            )
        }
    }

    fun addWateringFrequency(value: WateringFrequency) {
        val items = uiState.value.wateringFrequencies

        val newItems = items.toMutableSet().apply {
            add(value)
        }

        updateUiState {
            it.copy(
                wateringFrequencies = newItems.toHashSet()
            )
        }
    }

    @AssistedFactory
    interface ViewModelFactory {

        fun create(filters: PlantFilters): FiltersViewModel
    }
}