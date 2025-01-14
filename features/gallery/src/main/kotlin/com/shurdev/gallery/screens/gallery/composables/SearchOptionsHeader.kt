package com.shurdev.gallery.screens.gallery.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.shurdev.domain.models.plant.SortType
import com.shurdev.gallery.R
import com.shurdev.ui_kit.utils.toResString

@Composable
fun SearchOptionsHeader(
    onSortingClick: () -> Unit,
    onFiltersClick: () -> Unit,
    filtersCount: Int,
    selectedSorting: SortType,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextButton(
            onClick = onSortingClick,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(R.drawable.icon_sort),
                    contentDescription = null,
                )

                Spacer(Modifier.width(10.dp))

                Text(
                    text = selectedSorting.toResString(),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                    ),
                )
            }
        }

        Spacer(Modifier.weight(1f))

        TextButton(
            onClick = onFiltersClick,
        ) {

            val filtersString = stringResource(R.string.filters)

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = filtersString,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                    ),
                )

                Spacer(Modifier.width(10.dp))

                BadgedBox(
                    badge = {
                        if (filtersCount > 0) {
                            Badge {
                                Text("$filtersCount")
                            }
                        }
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.icon_filters),
                        contentDescription = null,
                    )
                }
            }
        }
    }
}