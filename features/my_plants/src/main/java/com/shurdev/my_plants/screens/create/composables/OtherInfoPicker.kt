package com.shurdev.my_plants.screens.create.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.shurdev.domain.models.myPlant.PlantOtherInfo
import com.shurdev.domain.models.plant.Illumination
import com.shurdev.domain.models.plant.ToxicCategory
import com.shurdev.ui_kit.R
import com.shurdev.ui_kit.buttons.MultiPickerButton
import com.shurdev.ui_kit.buttons.SingleChoiceDialogButton
import com.shurdev.ui_kit.cards.ExpandableCard
import com.shurdev.ui_kit.utils.toResString

@Composable
fun OtherInfoPicker(
    otherInfo: PlantOtherInfo?,
    onOtherInfoSelection: (Boolean) -> Unit,
    onIlluminationChanged: (Illumination) -> Unit,
    onToxicCategorySelected: (ToxicCategory) -> Unit,
    onToxicCategoryUnselected: (ToxicCategory) -> Unit,
) {
    val otherInfoTitle = stringResource(R.string.other)

    ExpandableCard(
        title = otherInfoTitle,
        onChanged = onOtherInfoSelection,
        isExpandedByDefault = false,
    ) {
        val illuminationTitle = stringResource(R.string.illumination)
        val toxicityTitle = stringResource(R.string.toxicity)
        val notSelectedText = stringResource(R.string.no_selection)

        Column {
            SingleChoiceDialogButton(
                modifier = Modifier.padding(8.dp),
                title = illuminationTitle,
                selectedItem = otherInfo?.illumination,
                itemToString = { it.toResString() },
                placeholderText = notSelectedText,
                items = Illumination.entries.toList(),
                onItemSelected = onIlluminationChanged,
            )

            Spacer(Modifier.height(16.dp))

            MultiPickerButton(
                modifier = Modifier.padding(8.dp),
                title = toxicityTitle,
                itemToString = { it.toResString() },
                items = ToxicCategory.entries.toHashSet(),
                selectedItems = otherInfo?.toxicCategories ?: hashSetOf(),
                onItemSelected = onToxicCategorySelected,
                onItemUnselected = onToxicCategoryUnselected,
            )
        }
    }
}