package com.shurdev.ui_kit.buttons

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.shurdev.domain.models.plant.WateringFrequency
import com.shurdev.ui_kit.R

@Composable
fun <T> SingleChoiceDialogButton(
    modifier: Modifier = Modifier,
    title: String,
    items: List<T>,
    onItemSelected: (T) -> Unit,
    itemToString: @Composable (T) -> String,
    placeholderText: String? = null,
    selectedItem: T? = null,
) {
    val typography = MaterialTheme.typography

    var expanded by remember { mutableStateOf(false) }

    val interactionSource = remember { MutableInteractionSource() }

    Column {
        Box(
            modifier = Modifier.clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                expanded = true
            },
        ) {
            Row(
                modifier = modifier,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = title,
                    style = typography.titleSmall
                )

                Spacer(Modifier.weight(1f))

                if (selectedItem != null) {
                    Text(itemToString(selectedItem))
                } else if (placeholderText != null) {
                    Text(placeholderText)
                }

                Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, null)
            }
        }

        if (!expanded) {
            return
        }

        SingleChoiceDialog(
            items = items,
            selectedItem = selectedItem,
            itemToString = itemToString,
            onSelect = { selected ->
                selected?.let(onItemSelected)
                expanded = false
            },
            onDismiss = {
                expanded = false
            },
        )
    }
}

@Composable
fun <T> SingleChoiceDialog(
    items: List<T>,
    selectedItem: T?,
    itemToString: @Composable (T) -> String,
    onSelect: (T) -> Unit,
    onDismiss: () -> Unit,
) {
    var selectedDialogItem by remember { mutableStateOf(selectedItem) }

    Dialog(
        onDismissRequest = onDismiss,
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            items.forEach { item ->
                val selected = item == selectedDialogItem

                val interactionSource = remember { MutableInteractionSource() }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            interactionSource = interactionSource,
                            indication = LocalIndication.current
                        ) {
                            selectedDialogItem = item
                        },
                ) {
                    RadioButton(
                        selected = selected,
                        interactionSource = interactionSource,
                        onClick = {
                            selectedDialogItem = item
                        },
                    )

                    Spacer(Modifier.width(8.dp))

                    Text(
                        text = itemToString(item),
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }

            val confirmText = stringResource(R.string.continue_text)
            val cancelText = stringResource(R.string.cancel)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Absolute.Right,
            ) {
                TextButton(
                    onClick = onDismiss,
                    modifier = Modifier.padding(8.dp),
                ) {
                    Text(cancelText)
                }

                TextButton(
                    onClick = {
                        selectedDialogItem?.let(onSelect)
                    },
                    modifier = Modifier.padding(8.dp),
                ) {
                    Text(confirmText)
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewSingleChoice() {
    Surface {
        SingleChoiceDialog(
            items = WateringFrequency.entries,
            onDismiss = {},
            onSelect = {},
            selectedItem = null,
            itemToString = { it.name },
        )
    }
}