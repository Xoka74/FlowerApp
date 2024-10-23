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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FlowerCategoryItem(
    category: String,
    onCategoryClick: (String) -> Unit
) {
    OutlinedCard(
        modifier = Modifier
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