package com.shurdev.ui_kit.cards

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ExpandableCard(
    modifier: Modifier = Modifier,
    title: String,
    isExpandedByDefault: Boolean = false,
    onChanged: (Boolean) -> Unit = {},
    content: @Composable () -> Unit,
) {
    var expanded by remember { mutableStateOf(isExpandedByDefault) }

    Card(
        modifier = modifier,
    ) {
        ExpandableCardTitle(
            modifier = Modifier.padding(8.dp),
            title = title,
            expanded = expanded,
            onCheckedChange = {
                expanded = !expanded
                onChanged(expanded)
            }
        )

        AnimatedVisibility(visible = expanded) {
            content()
        }
    }
}

@Composable
internal fun ExpandableCardTitle(
    title: String,
    expanded: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val typography = MaterialTheme.typography

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Start,
            text = title,
            style = typography.titleMedium,
        )

        Switch(
            checked = expanded,
            onCheckedChange = onCheckedChange,
        )
    }
}

@Preview
@Composable
fun PreviewExpandableCard() {
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        listOf(true, false).forEach {
            ExpandableCard(
                modifier = Modifier.fillMaxWidth(),
                title = "Полив",
                isExpandedByDefault = it,
            ) {
                Column(
                    modifier = Modifier.padding(10.dp),
                ) {
                    Text(
                        text = "Текст внутри карточки"
                    )
                }
            }
        }
    }
}