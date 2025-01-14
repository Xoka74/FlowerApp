package com.shurdev.gallery.screens.gallery.viewModel

import androidx.lifecycle.viewModelScope
import com.shurdev.domain.models.plant.PlantFilters
import com.shurdev.domain.models.plant.SearchOptions
import com.shurdev.domain.models.plant.SortType
import com.shurdev.domain.repositories.PlantRepository
import com.shurdev.ui_kit.viewModel.base.BaseViewModel
import com.shurdev.utils.runSuspendCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val plantRepository: PlantRepository,
) : BaseViewModel<GalleryUiState>(GalleryLoadingState) {
    private val _searchOptions = MutableStateFlow(SearchOptions())
    val searchOptions = _searchOptions.asStateFlow()

    init {
        subscribeToChanges()
    }

    private fun subscribeToChanges() {
        viewModelScope.launch { searchOptions.collect { loadPlants() } }
    }

    fun updateFilters(newValue: PlantFilters) {
        if (newValue == searchOptions.value.filters) return

        _searchOptions.update { it.copy(filters = newValue) }
    }

    fun updateSorting(sortType: SortType) {
        _searchOptions.update { it.copy(sorting = sortType) }
    }

    fun updateSearchText(text: String) {
        _searchOptions.update { it.copy(search = text) }
    }

    private fun loadPlants() {
        updateUiState { GalleryLoadingState }
        viewModelScope.launch {
            runSuspendCatching {
                val plants = plantRepository.getPlants(searchOptions.value)
                updateUiState { GalleryLoadedState(plants = plants) }
            }.onFailure {
                updateUiState { GalleryLoadingErrorState }
            }
        }
    }
}