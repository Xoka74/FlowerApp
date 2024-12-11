package com.shurdev.my_plants.screens.create.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.shurdev.domain.models.myPlant.PlantWatering
import com.shurdev.domain.models.plant.WateringFrequency
import com.shurdev.ui_kit.R
import com.shurdev.ui_kit.buttons.DatePickerButton
import com.shurdev.ui_kit.buttons.SingleChoiceDialogButton
import com.shurdev.ui_kit.cards.ExpandableCard
import com.shurdev.ui_kit.utils.toResString
import java.time.LocalDateTime

@Composable
fun WateringPicker(
    watering: PlantWatering?,
    onWateringSelectionChanged: (Boolean) -> Unit,
    onWateringFrequencyChanged: (WateringFrequency) -> Unit,
    onLastWateringTimeChanged: (LocalDateTime) -> Unit,
) {
    val wateringTitle = stringResource(R.string.watering)

    ExpandableCard(
        title = wateringTitle,
        onChanged = onWateringSelectionChanged,
        isExpandedByDefault = false,
    ) {
        Column {
            val wateringFrequencyTitle = stringResource(R.string.watering_frequency)
            val lastWateringTitle = stringResource(R.string.last_watering)

            SingleChoiceDialogButton(
                modifier = Modifier.padding(8.dp),
                title = wateringFrequencyTitle,
                selectedItem = watering?.frequency,
                itemToString = { it.toResString() },
                items = WateringFrequency.entries.toList(),
                onItemSelected = onWateringFrequencyChanged,
            )

            Spacer(Modifier.height(16.dp))

            DatePickerButton(
                modifier = Modifier.padding(8.dp),
                title = lastWateringTitle,
                selectedDateTime = watering?.lastWateringTime,
                onDateChanged = onLastWateringTimeChanged,
            )
        }
    }
}