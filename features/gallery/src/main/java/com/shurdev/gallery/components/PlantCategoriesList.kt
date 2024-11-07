package com.shurdev.gallery.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun PlantCategoriesList(
    categories: List<String>,
    onCategoryClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier
            .padding(top = 12.dp),
        contentPadding = PaddingValues(start = 16.dp),
    ) {
        items(categories) { category ->
            PlantCategoryItem(
                category = category,
                onCategoryClick = onCategoryClick,
            )
        }
    }
}
