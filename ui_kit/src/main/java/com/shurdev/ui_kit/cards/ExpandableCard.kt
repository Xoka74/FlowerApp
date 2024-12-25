package com.shurdev.ui_kit.cards

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shurdev.ui_kit.theme.FlowerAppTheme

@Composable
fun ExpandableCard(
    modifier: Modifier = Modifier,
    title: String,
    expanded: Boolean,
    onChanged: (Boolean) -> Unit = {},
    content: @Composable () -> Unit,
) {
    val colors = MaterialTheme.colorScheme

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = colors.surfaceContainer,
            contentColor = colors.primary
        )
    ) {
        ExpandableCardTitle(
            modifier = Modifier.padding(8.dp),
            title = title,
            expanded = expanded,
            onCheckedChange = onChanged,
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
    FlowerAppTheme {
        Surface {
            val typography = MaterialTheme.typography
            val colors = MaterialTheme.colorScheme

            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                listOf(true, false).forEach {
                    ExpandableCard(
                        modifier = Modifier.fillMaxWidth(),
                        title = "Полив",
                        expanded = it
                    ) {
                        Column(
                            modifier = Modifier.padding(10.dp),
                        ) {
                            Text(
                                text = "Текст внутри карточки",
                                style = typography.titleMedium.copy(
                                    color = colors.primary
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}