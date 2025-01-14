package com.shurdev.gallery.screens.filters

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shurdev.domain.models.plant.Illumination
import com.shurdev.domain.models.plant.PlantFilters
import com.shurdev.domain.models.plant.ToxicCategory
import com.shurdev.domain.models.plant.WateringFrequency
import com.shurdev.gallery.screens.filters.viewModel.FiltersViewModel
import com.shurdev.ui_kit.R
import com.shurdev.ui_kit.buttons.MultiPickerButton
import com.shurdev.ui_kit.buttons.PrimaryButton
import com.shurdev.ui_kit.buttons.SecondaryButton
import com.shurdev.ui_kit.utils.toResString

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FiltersBottomSheet(
    onDismiss: () -> Unit,
    onFiltersSuccess: (PlantFilters) -> Unit,
    initialFilters: PlantFilters,
) {
    val viewModel = hiltViewModel<FiltersViewModel, FiltersViewModel.ViewModelFactory> { factory ->
        factory.create(filters = initialFilters)
    }

    val filtersState by viewModel.uiState.collectAsState()

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
        contentWindowInsets = { BottomSheetDefaults.windowInsets },
        sheetState = sheetState,
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            MultiPickerButton(
                modifier = Modifier.padding(8.dp),
                title = stringResource(R.string.toxicity),
                itemToString = { it.toResString() },
                items = ToxicCategory.entries.toHashSet(),
                selectedItems = filtersState.toxicCategories,
                onItemSelected = viewModel::addToxicCategory,
                onItemUnselected = viewModel::removeToxicCategory,
            )

            Spacer(Modifier.height(12.dp))

            MultiPickerButton(
                modifier = Modifier.padding(8.dp),
                title = stringResource(R.string.illumination),
                itemToString = { it.toResString() },
                items = Illumination.entries.toHashSet(),
                selectedItems = filtersState.illuminations,
                onItemSelected = viewModel::addIllumination,
                onItemUnselected = viewModel::removeIllumination,
            )

            Spacer(Modifier.height(12.dp))

            MultiPickerButton(
                modifier = Modifier.padding(8.dp),
                title = stringResource(R.string.watering_frequency),
                itemToString = { it.toResString() },
                items = WateringFrequency.entries.toHashSet(),
                selectedItems = filtersState.wateringFrequencies,
                onItemSelected = viewModel::addWateringFrequency,
                onItemUnselected = viewModel::removeWateringFrequency,
            )

            Spacer(Modifier.height(12.dp))

            Row {
                if (filtersState != PlantFilters()) {
                    SecondaryButton(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 12.dp),
                        text = stringResource(R.string.reset),
                        onClick = viewModel::resetFilters,
                    )
                }

                PrimaryButton(
                    modifier = Modifier.weight(1f),
                    text = stringResource(R.string.apply),
                    onClick = {
                        onFiltersSuccess(filtersState)
                        onDismiss()
                    },
                )
            }
        }
    }
}