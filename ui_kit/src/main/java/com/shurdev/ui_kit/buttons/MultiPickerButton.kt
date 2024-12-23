package com.shurdev.ui_kit.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shurdev.domain.models.plant.ToxicCategory

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun <T> MultiPickerButton(
    modifier: Modifier = Modifier,
    title: String,
    onItemSelected: (T) -> Unit,
    onItemUnselected: (T) -> Unit,
    itemToString: @Composable (T) -> String,
    items: HashSet<T>,
    selectedItems: HashSet<T> = hashSetOf(),
) {
    val typography = MaterialTheme.typography

    Column(modifier) {
        Text(
            text = title,
            textAlign = TextAlign.Start,
            style = typography.titleSmall,
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(Modifier.height(8.dp))

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items.forEach { item ->
                val selected = selectedItems.contains(item)

                FilterChip(
                    selected = selected,
                    label = { Text(itemToString(item)) },
                    leadingIcon = {
                        if (selected) {
                            Icon(
                                imageVector = Icons.Filled.Done,
                                modifier = Modifier.size(FilterChipDefaults.IconSize),
                                contentDescription = null,
                            )
                        }
                    },
                    onClick = {
                        if (selected) {
                            onItemUnselected(item)
                        } else {
                            onItemSelected(item)
                        }
                    },
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewMultiPickerButton() {
    var items by remember { mutableStateOf(ToxicCategory.entries.toHashSet()) }

    Surface {
        MultiPickerButton(
            title = "Токсичность",
            itemToString = { it.name },
            selectedItems = items,
            items = ToxicCategory.entries.toHashSet(),
            onItemSelected = {
                val newItems = items.toMutableSet().apply {
                    add(it)
                }.toHashSet()

                items = newItems
            },
            onItemUnselected = {
                val newItems = items.filter { item -> item != it }.toHashSet()

                items = newItems
            },
        )
    }
}