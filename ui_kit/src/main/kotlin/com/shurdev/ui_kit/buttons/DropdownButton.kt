package com.shurdev.ui_kit.buttons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shurdev.domain.models.plant.WateringFrequency
import com.shurdev.ui_kit.theme.FlowerAppTheme
import com.shurdev.ui_kit.utils.toResString

@Composable
fun <T> DropdownButton(
    modifier: Modifier = Modifier,
    title: String,
    items: List<T>,
    onItemSelected: (T) -> Unit,
    itemToString: @Composable (T) -> String,
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
                }

                Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, null)
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            items.map { item ->
                DropdownMenuItem(
                    text = { Text(itemToString(item)) },
                    onClick = {
                        onItemSelected(item)
                        expanded = false
                    }
                )
            }

        }
    }
}

@Preview
@Composable
fun PreviewDropdownButton() {
    val frequency by remember { mutableStateOf(WateringFrequency.OnceAWeek) }
    FlowerAppTheme {
        Surface {
            DropdownButton(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                title = "Частота полива",
                selectedItem = frequency,
                itemToString = { it.toResString() },
                items = WateringFrequency.entries.toList(),
                onItemSelected = {

                },
            )
        }
    }
}