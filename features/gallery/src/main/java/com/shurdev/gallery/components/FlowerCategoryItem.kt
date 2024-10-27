package com.shurdev.gallery.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shurdev.gallery.mocks.CATEGORIES

@Composable
internal fun FlowerCategoryItem(
    modifier: Modifier = Modifier,
    category: String,
    onCategoryClick: (String) -> Unit
) {
    OutlinedCard(
        modifier = modifier
            .padding(end = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(20.dp),
        onClick = { onCategoryClick(category) }
    ) {
        Column {
            Text(
                modifier = Modifier
                    .padding(
                        vertical = 4.dp,
                        horizontal = 12.dp
                    ),
                text = category,
                fontSize = 14.sp
            )
        }
    }
}

@Preview
@Composable
internal fun FlowerCategoryItemPreview() {
    FlowerCategoryItem(
        category = CATEGORIES[0],
        onCategoryClick = {}
    )
}