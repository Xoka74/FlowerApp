package com.shurdev.gallery.screens.gallery

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shurdev.domain.models.plant.Plant
import com.shurdev.domain.models.plant.PlantFilters
import com.shurdev.domain.models.plant.SortType
import com.shurdev.gallery.R
import com.shurdev.gallery.components.PlantsList
import com.shurdev.gallery.mocks.Plants
import com.shurdev.gallery.screens.filters.FiltersBottomSheet
import com.shurdev.gallery.screens.gallery.composables.EmptyListPlaceholder
import com.shurdev.gallery.screens.gallery.composables.SearchOptionsHeader
import com.shurdev.gallery.screens.gallery.viewModel.GalleryLoadedState
import com.shurdev.gallery.screens.gallery.viewModel.GalleryLoadingErrorState
import com.shurdev.gallery.screens.gallery.viewModel.GalleryLoadingState
import com.shurdev.gallery.screens.gallery.viewModel.GalleryUiState
import com.shurdev.gallery.screens.gallery.viewModel.GalleryViewModel
import com.shurdev.ui_kit.buttons.PrimaryButton
import com.shurdev.ui_kit.dialogs.SingleChoiceDialog
import com.shurdev.ui_kit.errors.ErrorView
import com.shurdev.ui_kit.fields.SearchField
import com.shurdev.ui_kit.layouts.Center
import com.shurdev.ui_kit.layouts.DefaultScreenLayout
import com.shurdev.ui_kit.loaders.Loader
import com.shurdev.ui_kit.utils.toResString

@Composable
internal fun GalleryRoute(
    onPlantClick: (Plant) -> Unit,
) {
    val galleryViewModel = hiltViewModel<GalleryViewModel>()

    val uiState by galleryViewModel.uiState.collectAsState()
    val searchOptions by galleryViewModel.searchOptions.collectAsState()

    GalleryScreen(
        uiState = uiState,
        filters = searchOptions.filters,
        sorting = searchOptions.sorting,
        onPlantClick = onPlantClick,
        onSearchTextChange = galleryViewModel::updateSearchText,
        onFiltersUpdated = galleryViewModel::updateFilters,
        onSortingUpdated = galleryViewModel::updateSorting,
        onTryAgain = galleryViewModel::loadPlants,
    )
}

@Composable
internal fun GalleryScreen(
    uiState: GalleryUiState,
    filters: PlantFilters,
    sorting: SortType,
    onPlantClick: (Plant) -> Unit,
    onSearchTextChange: (String) -> Unit,
    onSortingUpdated: (SortType) -> Unit,
    onFiltersUpdated: (PlantFilters) -> Unit,
    onTryAgain: () -> Unit,
) {
    var isFiltersVisible by remember { mutableStateOf(false) }
    var isSortingVisible by remember { mutableStateOf(false) }

    DefaultScreenLayout(
        title = stringResource(R.string.search),
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
        ) {
            SearchField(
                hint = stringResource(R.string.search_hint),
                onSearchTextChange = onSearchTextChange,
                debounceTimeMillis = 300L,
            )

            Spacer(Modifier.height(12.dp))

            SearchOptionsHeader(
                filtersCount = filters.selectedCount(),
                selectedSorting = sorting,
                onFiltersClick = { isFiltersVisible = true },
                onSortingClick = { isSortingVisible = true },
            )

            Spacer(Modifier.height(12.dp))

            when (uiState) {
                GalleryLoadingState -> Center {
                    Loader()
                }

                GalleryLoadingErrorState -> Center {
                    ErrorView()

                    Spacer(Modifier.height(8.dp))

                    PrimaryButton(
                        text = stringResource(com.shurdev.ui_kit.R.string.try_again),
                        onClick = onTryAgain
                    )
                }

                is GalleryLoadedState -> when (uiState.plants.isEmpty()) {
                    true -> EmptyListPlaceholder()
                    false -> PlantsList(
                        plants = uiState.plants,
                        onPlantClick = onPlantClick
                    )
                }
            }

            if (isFiltersVisible) {
                FiltersBottomSheet(
                    initialFilters = filters,
                    onFiltersSuccess = onFiltersUpdated,
                    onDismiss = { isFiltersVisible = false },
                )
            }

            if (isSortingVisible) {
                SingleChoiceDialog(
                    items = SortType.entries,
                    initialSelectedItem = sorting,
                    itemToString = { it.toResString() },
                    onDismiss = { isSortingVisible = false },
                    onSelect = {
                        isSortingVisible = false
                        onSortingUpdated(it)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
internal fun GalleryScreenPreview() {
    GalleryScreen(
        uiState = GalleryLoadedState(
            plants = Plants
        ),
        onPlantClick = {},
        onSearchTextChange = {},
        filters = PlantFilters(),
        sorting = SortType.ByNameAscending,
        onFiltersUpdated = {},
        onSortingUpdated = {},
        onTryAgain = {},
    )
}